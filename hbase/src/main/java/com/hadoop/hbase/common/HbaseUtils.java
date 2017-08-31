package com.hadoop.hbase.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * @Auther 陈郑游
 * @Data 2017/8/31 0031
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HbaseUtils {

    private static final String IP_URL = "192.168.92.11";
    private static final String HZQ = "hbase.zookeeper.quorum";

    /**
     * 获取hbase的配置文件信息
     *
     * @return Configuration
     */
    public static Configuration getHBaseConfiguration() {
        Configuration conf = HBaseConfiguration.create();
        conf.set( HZQ , IP_URL);
        return conf;
    }
}
