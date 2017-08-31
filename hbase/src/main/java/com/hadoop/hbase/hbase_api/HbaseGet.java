package com.hadoop.hbase.hbase_api;

import com.hadoop.hbase.common.HbaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther 陈郑游
 * @Data 2017/8/31 0031
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HbaseGet {

    public static void main(String[] args) throws Exception {
        Configuration conf = HbaseUtils.getHBaseConfiguration();
        testUseHbaseConnectionPool(conf);
    }


    // 连接池
    public static void testUseHbaseConnectionPool(Configuration conf) throws IOException {
        ExecutorService threads = Executors.newFixedThreadPool(10);
        HConnection pool = HConnectionManager.createConnection(conf, threads);
        HTableInterface hTable = pool.getTable("users");
        try {

            testGet(hTable);
        } finally {
            hTable.close(); // 每次htable操作完 关闭 其实是放到pool中
            pool.close(); // 最终的时候关闭
        }
    }



    /**
     * 测试get命令
     *
     * @param hTable
     * @throws IOException
     */
    public static void testGet(HTableInterface hTable) throws IOException {
        byte[] family = Bytes.toBytes("f");

        Get get = new Get(Bytes.toBytes("row1"));
        Result result = hTable.get(get);
        byte[] buf = result.getValue(family, Bytes.toBytes("id"));

        System.out.println("id:" + Bytes.toString(buf));
        buf = result.getValue(family, Bytes.toBytes("age"));
        System.out.println("age:" + Bytes.toInt(buf));
        buf = result.getValue(family, Bytes.toBytes("name"));
        System.out.println("name:" + Bytes.toString(buf));
        buf = result.getRow();
        System.out.println("row:" + Bytes.toString(buf));
    }
}
