package com.czy.hadoop.controller;

import com.czy.hadoop.common.AnEnConstants;
import com.czy.hadoop.model.Message;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
@Controller
public class DimensionController extends AnEnBaseController {

    @Resource(name = "dimensionTableMapping")
    private Map<String, String> dimensionTableMapping;

    @Resource(name = "dimensionColumns")
    private Map<String, String> dimensionColumns;

    @RequestMapping(value = "/stats/getDimensions", method = RequestMethod.GET)
    @ResponseBody
    public Object getDimensions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dimens = request.getParameter(AnEnConstants.DIMENSIONS);
        String[] dimensions = StringUtils.split(dimens, AnEnConstants.SEPARTION_COMMA);
        if (null == dimensions) {
            return Message.badRequest("请求参数无效,必须给定dimensions参数");
        }
        for (String dimension : dimensions) {
            if (!dimensionTableMapping.containsKey(dimension)) {
                return Message.badRequest("dimensions参数无效" + dimension);
            }
        }

        List<Map<String, Object>> data = null;
        Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        for (String dimension : dimensions) {
            if (!result.containsKey(dimension)) {
                paramsMap.clear();
                if (dimensionColumns.containsKey(dimension)) {
                    paramsMap.put(AnEnConstants.SELECT_COLUMNS, dimensionColumns.get(dimension));
                }
                String tableName = dimensionTableMapping.get(dimension);
                paramsMap.put(AnEnConstants.TABLE_NAME, tableName);
                if (dimension.startsWith(AnEnConstants.LOCATION)) {
                    paramsMap.put(AnEnConstants.DIMENSION_NAME, dimension);
                    paramsMap.put(AnEnConstants.COUNTRY_NAME, request.getParameter(AnEnConstants.COUNTRY_NAME));
                    paramsMap.put(AnEnConstants.PROVINCE_NAME, request.getParameter(AnEnConstants.PROVINCE_NAME));
                }
                data = dimensionService.queryDimensionData(paramsMap);
                result.put(dimension, data);
            }
        }

        return Message.ok(result);
    }
}
