package com.czy.hadoop.service;

import java.util.List;
import java.util.Map;

/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface DimensionDao {

    public List<Map<String, Object>> queryDimensionData(Map<String, String> queryMap);
}
