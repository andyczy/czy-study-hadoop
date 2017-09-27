package com.czy.hadoop.javasdk.test;

import com.czy.hadoop.javasdk.AnalyticsEngineSDK;

/**
 * @Auther 陈郑游
 * @Data 2017/7/26 0026
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 */
public class JavaSdk_test {

    // 启动 nginx   ----------   [root@chenzy-1 sbin]# ./nginx &
    // 启动 flume   ----------
    public static void main(String[] args) {
        AnalyticsEngineSDK.onChargeSuccess("orderid123", "gerryliu123");
        AnalyticsEngineSDK.onChargeRefund("orderid456", "gerryliu456");
    }


}
