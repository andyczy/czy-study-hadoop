package com.czy.hadoop.controller;

import com.czy.hadoop.common.AnEnConstants;
import com.czy.hadoop.model.Message;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Auther 陈郑游
 * @Data 2017/9/9 0009
 * @Description:
 * * 处理数据请求接口<br/>
 * rest api summary: /stats/summary/{bucket}?metric=xxx&其他参数<br/>
 * rest api group by: /stats/groupBy/{bucket}?metric=xxx&group_by=xxx&其他参数<br/>
 * 返回值是：json格式的数据，参数MessageEntry
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
@Controller
public class AnEnController extends AnEnBaseController {

    // 日志
    private static Logger log = Logger.getLogger(AnEnController.class);


    /**
     * 处理bucket请求
     */
    @RequestMapping(value = "/stats/summary/{"+ AnEnConstants.BUCKET +"}")
    @ResponseBody
    public Object getSummary(@PathVariable(AnEnConstants.BUCKET) String bucket,
                             HttpServletRequest request, HttpServletResponse response){
        String groupBy = request.getParameter(AnEnConstants.GROUP_BY);
        if (StringUtils.isNotBlank(groupBy)) {
            return Message.badRequest("bucket summary can't be group by");
        }
        return this.doProcess(bucket, request);
    }


    /**
     * 处理group by请求
     */
    @RequestMapping(value = "/stats/groupBy/{" + AnEnConstants.BUCKET + "}")
    @ResponseBody
    public Object getGroupBy(@PathVariable(AnEnConstants.BUCKET) String bucket, HttpServletRequest request, HttpServletResponse response) {
        String groupBy = request.getParameter(AnEnConstants.GROUP_BY);
        if (StringUtils.isBlank(groupBy)) {
            return Message.badRequest("bucket group by must be give group by parameter");
        }
        return this.doProcess(bucket, request);
    }


    /**
     * 具体的处理方法
     */
    private Object doProcess(String bucket, HttpServletRequest request) {

        return null;
    }


}
