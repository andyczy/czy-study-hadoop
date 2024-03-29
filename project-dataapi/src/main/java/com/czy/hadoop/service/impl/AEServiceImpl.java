package com.czy.hadoop.service.impl;

import com.czy.hadoop.calculate.AnEnCalculate;
import com.czy.hadoop.common.AnEnConstants;
import com.czy.hadoop.dao.mybatis.AEDao;
import com.czy.hadoop.model.QueryModel;
import com.czy.hadoop.service.AnEnService;
import com.czy.hadoop.util.ApplicationContextUtil;

import java.util.*;



 /**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description: 默认的aeservice实现类
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class AEServiceImpl implements AnEnService {
    private AEDao aeDao;

    public void setAeDao(AEDao aeDao) {
        this.aeDao = aeDao;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> execute(QueryModel queryModel) {
        List<Map<String, Object>> metricData = aeDao.fetchMetricData(queryModel);
        if (metricData == null) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Set<String>> metrics = queryModel.getMetrics();
        Set<String> metricKeys = metrics.keySet();
        Set<String> groups = queryModel.getGroups();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> metricDataItem : metricData) {
            if (metricDataItem == null) {
                continue;
            }
            Set<String> allCloumn = metricDataItem.keySet();
            Map<String, Object> resMap = new HashMap<String, Object>();

            for (String metricKey : metricKeys) {
                Set<String> metricCloumns = metrics.get(metricKey);
                Map<String, Object> metricCloumnValues = new HashMap<String, Object>();
                for (String metricCloumn : metricCloumns) {
                    Object metricCloumnValue = metricDataItem.get(metricCloumn);
                    if (metricCloumnValue == null) {
                        metricCloumnValue = 0;
                    }
                    if (AnEnConstants.KPI_NAME.equals(metricCloumn)) {
                        metricCloumnValues.put(metricCloumn, metricCloumnValue);
                    } else {
                        metricCloumnValues.put(AnEnConstants.PRIFIX + metricCloumn, metricCloumnValue);
                    }
                }

                // 获取后期计算calculate对象
                String beanId = queryModel.getBucket() + AnEnConstants.DELIMITER + metricKey;
                AnEnCalculate aeCalculate = null;
                if (ApplicationContextUtil.getApplicationContext().containsBean(beanId)) {
                    aeCalculate = (AnEnCalculate) ApplicationContextUtil.getApplicationContext().getBean(beanId);
                }

                // 根据是否有calculate对象来进行数据填充操作
                if (aeCalculate == null) {
                    resMap.putAll(metricCloumnValues);
                } else {
                    Object calculateRes = aeCalculate.calculate(metricCloumnValues);
                    if (calculateRes instanceof Map) {
                        resMap.putAll((Map<String, Object>) calculateRes);
                    } else {
                        resMap.put(AnEnConstants.PRIFIX + metricKey, calculateRes);
                    }
                }
            }

            // 处理分组情况
            if (groups != null) {
                for (String cloumn : allCloumn) {
                    if (cloumn.startsWith(AnEnConstants.GROUP_FLAG)) {
                        String c = cloumn.replace(AnEnConstants.GROUP_FLAG, AnEnConstants.EMPTY_STR);
                        resMap.put(c, metricDataItem.get(cloumn));
                    }
                }
            }

            // 添加到最终集合中
            result.add(resMap);
        }
        return result;
    }
}