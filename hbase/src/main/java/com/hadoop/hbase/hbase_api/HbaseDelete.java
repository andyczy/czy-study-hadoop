package com.hadoop.hbase.hbase_api;

import com.hadoop.hbase.common.HbaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @Auther 陈郑游
 * @Data 2017/8/31 0031
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HbaseDelete {

    public static void main(String[] args) throws Exception {
        Configuration conf = HbaseUtils.getHBaseConfiguration();
        testUseHTable(conf);
    }

    public static void testUseHTable(Configuration conf) throws IOException {
        HTable hTable = new HTable(conf, "users");
        try {

            testDelete(hTable);
        } finally {
            hTable.close();
        }
    }

    /**
     * 测试delete
     *
     * @param hTable
     * @throws IOException
     */
   public static void testDelete(HTableInterface hTable) throws IOException {
        byte[] family = Bytes.toBytes("f");

        Delete delete = new Delete(Bytes.toBytes("row3"));
        // 删除列
        delete = delete.deleteColumn(family, Bytes.toBytes("id"));
        // 直接删除family
        // delete.deleteFamily(family);
        hTable.delete(delete);
        System.out.println("删除成功");
    }
}
