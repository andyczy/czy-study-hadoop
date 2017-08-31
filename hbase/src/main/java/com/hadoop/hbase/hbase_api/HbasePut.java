package com.hadoop.hbase.hbase_api;

import com.hadoop.hbase.common.HbaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther 陈郑游
 * @Data 2017/8/31 0031
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HbasePut {

    public static void main(String[] args) throws Exception {
        Configuration conf = HbaseUtils.getHBaseConfiguration();
        testUseHTable(conf);
    }

    public static void testUseHTable(Configuration conf) throws IOException {
        HTable hTable = new HTable(conf, "users");
        try {

            testPut1(hTable);  //测试put操作:单个put
            testPut2(hTable);  //测试put操作:多个put
        } finally {
            hTable.close();
        }
    }


    /**
     * 测试put操作:单个put
     *
     * @param hTable
     * @throws IOException
     */
    public static void testPut1(HTableInterface hTable) throws IOException {
        Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes("f"), Bytes.toBytes("id"), Bytes.toBytes("11"));
        put.add(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("chenzy"));
        put.add(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(23));
        put.add(Bytes.toBytes("f"), Bytes.toBytes("phone"), Bytes.toBytes("0101-11111111"));
        put.add(Bytes.toBytes("f"), Bytes.toBytes("email"), Bytes.toBytes("649954910@qq.com"));
        hTable.put(put);
    }


    /**
     * 测试put操作: 多个put
     *
     * @param hTable
     * @throws IOException
     */
    public static void testPut2(HTableInterface hTable) throws IOException {

        Put put1 = new Put(Bytes.toBytes("row2"));
        put1.add(Bytes.toBytes("f"), Bytes.toBytes("id"), Bytes.toBytes("2"));
        put1.add(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("user2"));

        Put put2 = new Put(Bytes.toBytes("row3"));
        put2.add(Bytes.toBytes("f"), Bytes.toBytes("id"), Bytes.toBytes("3"));
        put2.add(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("user3"));

        Put put3 = new Put(Bytes.toBytes("row4"));
        put3.add(Bytes.toBytes("f"), Bytes.toBytes("id"), Bytes.toBytes("4"));
        put3.add(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("user4"));

        List<Put> list = new ArrayList<Put>();
        list.add(put1);
        list.add(put2);
        list.add(put3);
        hTable.put(list);

        // 检测put,条件成功就插入，要求rowkey是一样的。
        Put put4 = new Put(Bytes.toBytes("row5"));
        put4.add(Bytes.toBytes("f"), Bytes.toBytes("id"), Bytes.toBytes("7"));
        //
        hTable.checkAndPut(Bytes.toBytes("row5"), Bytes.toBytes("f"), Bytes.toBytes("id"), null, put4);
        System.out.println("插入成功");
    }
}
