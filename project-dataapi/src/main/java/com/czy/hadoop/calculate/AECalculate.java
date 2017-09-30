package com.czy.hadoop.calculate;

import java.util.Map;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface AECalculate {
    /**
     * 计算结果
     * 
     * @param metricData
     * @return
     */
    public Object calculate(Map<String, Object> metricData);
}
