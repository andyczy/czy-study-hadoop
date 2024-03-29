package com.czy.hadoop.controller;

import com.czy.hadoop.common.AnEnConstants;
import com.czy.hadoop.model.Message;
import com.czy.hadoop.model.QueryColumn;
import com.czy.hadoop.model.QueryModel;
import com.czy.hadoop.service.AnEnService;
import com.czy.hadoop.util.ApplicationContextUtil;
import com.czy.hadoop.util.SetUtil;
import com.czy.hadoop.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


 /**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 *  * 处理数据请求接口<br/>
 * rest api summary: /stats/summary/{bucket}?metric=xxx&其他参数<br/>
 * rest api group by: /stats/groupBy/{bucket}?metric=xxx&group_by=xxx&其他参数<br/>
 * 返回值是：json格式的数据，参数MessageEntry
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
@Controller
public class AnEnController extends AnEnBaseController {

    private static Logger log = Logger.getLogger(AnEnController.class);


    /**
     * 1、处理summary请求
     * @param bucket
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/stats/summary/{" + AnEnConstants.BUCKET + "}", method = RequestMethod.GET)
    @ResponseBody
    public Object getSummary(@PathVariable(AnEnConstants.BUCKET) String bucket,
                             HttpServletRequest request, HttpServletResponse response) {
        String groupBy = request.getParameter(AnEnConstants.GROUP_BY);
        if (StringUtils.isNotBlank(groupBy)) {    //不是summary、不执行操作
            return Message.badRequest("bucket summary can't be group by");
        }
        return this.doProcess(bucket, request);   //执行操作
    }


    /**
     * 2、处理group by请求
     * @param bucket
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/stats/groupBy/{" + AnEnConstants.BUCKET + "}", method = RequestMethod.GET)
    @ResponseBody
    public Object getGroupBy(@PathVariable(AnEnConstants.BUCKET) String bucket,
                             HttpServletRequest request, HttpServletResponse response) {
        String groupBy = request.getParameter(AnEnConstants.GROUP_BY);
        if (StringUtils.isBlank(groupBy)) {
            return Message.badRequest("bucket group by must be give group by parameter");
        }
        return this.doProcess(bucket, request);
    }

    /**
     * 具体的处理方法
     * @param bucket
     * @param request
     * @return
     */
    private Object doProcess(String bucket, HttpServletRequest request) {
        // 处理bucket，判断是否存在
        Set<String> bucketMetricKeys = this.bucketMetrics.keySet();
        if (!bucketMetricKeys.contains(bucket)) {
            return Message.badRequest("bucket not support:" + bucket);
        }

        // 处理metric，判断对应的bucket中是否有对应的metric存在
        String metric = request.getParameter(AnEnConstants.METRIC);
        if (StringUtils.isBlank(metric)) {
            return Message.badRequest("metric can't set to empty");
        }
        String[] metrics = StringUtils.split(metric, AnEnConstants.SEPARTION_COMMA);
        Set<Object> bucketMetricValues = this.bucketMetrics.get(bucket);
        for (String metricItem : metrics) {
            if (!SetUtil.contains(bucketMetricValues, metricItem)) {
                return Message.badRequest("Metric=" + metricItem + " doesn't exist of the bucket " + bucket);
            }
        }

        // 处理group by条件
        String groupBy = request.getParameter(AnEnConstants.GROUP_BY);
        Set<String> groups = new HashSet<String>(); // 最终解析存储
        if (StringUtils.isNotBlank(groupBy)) {
            Set<Object> groupByMetrics = this.groupByColumns.get(bucket);
            String[] attr = StringUtils.split(groupBy, AnEnConstants.SEPARTION_COMMA);
            for (String str : attr) {
                if (!SetUtil.contains(groupByMetrics, str)) {
                    return Message.badRequest("Bucket " + bucket + " can't group by " + str);
                }
                groups.add(str);
            }
        }

        // 处理日期
        String startDate = request.getParameter(AnEnConstants.START_DATE);
        String endDate = request.getParameter(AnEnConstants.END_DATE);
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            String date = request.getParameter(AnEnConstants.DATE);
            if (StringUtils.isBlank(date)) {
                return Message.badRequest("Please give the date parameters");
            }
            startDate = date;
            endDate = date;
        }
        if (!TimeUtil.checkDate(startDate) || !TimeUtil.checkDate(endDate) || 0 > endDate.compareTo(startDate)) {
            return Message.badRequest("Start date = " + startDate + ", end date = " + endDate + " are invalid arguments.");
        }

        // TODO: 本次项目只针对按天group by，如果需要按照周,月,年进行group by操作，那么需要进行两部多的步骤:
        // 1. 根据group by的请求，将日期进行修改
        // 2. 在querycolumn中添加一个属性dateType来区分不同的日期方式(存在)

        // 开始构建我们的查询对象
        QueryColumn queryColumn = new QueryColumn();
        queryColumn.setStartDate(startDate);
        queryColumn.setEndDate(endDate);

        // 处理参数： platform
        boolean paramStatus = true;
        paramStatus = this.setDimensionPlatformId(request, groups, queryColumn);
        if (!paramStatus) {
            return Message.badRequest("Dimension platform id or platform is invalid arguments");
        }

        // 开始创建每个metric对应的QueryModel
        Map<String, QueryModel> queryModels = new HashMap<String, QueryModel>();
        for (String metricItem : metrics) {
            String key = bucket + AnEnConstants.DELIMITER + metricItem;
            String queryId = this.bucketMetricQueryId.get(key);
            QueryModel model = queryModels.get(queryId);
            if (model == null) {
                model = new QueryModel(queryId);
                model.setBucket(bucket);
                queryModels.put(queryId, model);
            }

            Integer kpiDimensionId = this.dimensionService.getKpiDimensionId(metricItem);
            if (null == kpiDimensionId) {
                model.setKpiDimensionId(0);
            } else {
                model.setKpiDimensionId(kpiDimensionId);
            }

            model.addMetric(metricItem);
            // 返回值
            String columns = this.bucketMetricColumns.get(key);
            model.addFields(metric, columns);
            model.setQueryColumn(queryColumn);
            model.setGroups(groups);
        }

        // 进行返回对象定义，以及具体的查询操作
        List<Map<String, Object>> returnValue = null;
        Map<String, Class<?>> keyTypes = new HashMap<String, Class<?>>(); // 保存数据类型

        Set<String> queryIds = queryModels.keySet();
        for (String queryId : queryIds) {
            QueryModel model = queryModels.get(queryId);
            List<Map<String, Object>> dataItem = null;

            AnEnService extendedService = null;
            if (ApplicationContextUtil.getApplicationContext().containsBean(queryId)) {
                // 如果存在，表示给定的是一个特定的bean
                extendedService = (AnEnService) ApplicationContextUtil.getApplicationContext().getBean(queryId);
            } else {
                extendedService = this.aeService;
            }

            // 获取数据
            try {
                dataItem = extendedService.execute(model);
            } catch (Throwable e) {
                log.error("服务器执行异常", e);
                return Message.error("Server is error:" + e.getMessage());
            }

            // 处理返回值; 1. 获取类型参数， 方便后期进行填充。2. 将结果添加到returnValue这个map集合中
            this.registerKeyTypeMap(keyTypes, dataItem);

            if (returnValue != null && returnValue.size() != 0) {
                // 非空，填充
                Set<String> groupSet = model.getGroups();
                if (groupSet != null && groupSet.size() > 0) {
                    // 进行合并操作
                    for (int i = 0; i < returnValue.size(); i++) {
                        int j = 0;
                        Map<String, Object> oldMap = returnValue.get(i);
                        for (; i < dataItem.size();) {
                            boolean flag = true;
                            Map<String, Object> newMap = dataItem.get(j);
                            for (String groupColumn : groupSet) {
                                if (newMap != null && newMap.size() > 0 && newMap.get(groupColumn) != null) {
                                    if (!newMap.get(groupColumn).equals(oldMap.get(groupColumn))) {
                                        flag = false;
                                        break;
                                    }
                                }
                            }

                            if (flag) {
                                returnValue.get(i).putAll(dataItem.get(j));
                                dataItem.remove(j);
                            } else {
                                j++;
                            }
                        }
                    }
                    returnValue.addAll(dataItem); // 添加其他剩下的数据
                } else {
                    // 不是group by操作
                    for (int i = 0; i < dataItem.size(); i++) {
                        // 由于是按照时间先排序号的，可以直接添加
                        returnValue.get(i).putAll(dataItem.get(i));
                    }
                }
            } else {
                // 如果是第一次的情况下
                returnValue = dataItem;
            }
        }

        // 进行填充操作
        this.warpReturnValue(keyTypes, returnValue);
        return Message.ok(returnValue);
    }

    /**
     * 将数据类型保存下来，方便进行填充操作
     * 
     * @param keyTypes
     * @param items
     */
    private void registerKeyTypeMap(Map<String, Class<?>> keyTypes, List<Map<String, Object>> items) {
        for (Map<String, Object> item : items) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                keyTypes.put(entry.getKey(), entry.getValue().getClass());
            }
        }
    }

    /**
     * 填充默认数据
     * 
     * @param keyTypes
     * @param items
     */
    private void warpReturnValue(Map<String, Class<?>> keyTypes, List<Map<String, Object>> items) {
        for (Map<String, Object> item : items) {
            for (Map.Entry<String, Class<?>> entry : keyTypes.entrySet()) {
                if (!item.containsKey(entry.getKey()) && Number.class.isAssignableFrom(entry.getValue())) {
                    item.put(entry.getKey(), 0);
                }
            }
        }
    }
}
