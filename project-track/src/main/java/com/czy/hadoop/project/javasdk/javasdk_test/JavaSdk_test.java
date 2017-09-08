package com.czy.hadoop.project.javasdk.javasdk_test;

import com.czy.hadoop.project.javasdk.AnalyticsEngineSDK;

/**
 * @Auther 陈郑游
 * @Data 2017/7/26 0026
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 */
public class JavaSdk_test {

    public static void main(String[] args) {
        AnalyticsEngineSDK.onChargeSuccess("orderid123", "gerryliu123");
        AnalyticsEngineSDK.onChargeRefund("orderid456", "gerryliu456");
    }


}
