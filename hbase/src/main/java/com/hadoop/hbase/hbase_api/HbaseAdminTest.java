package com.hadoop.hbase.hbase_api;

import com.hadoop.hbase.common.HbaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * @Auther 陈郑游
 * @Data 2017/8/31 0031
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HbaseAdminTest {

    public static void main(String[] args) throws Exception {
        // get configuration
        Configuration conf = HbaseUtils.getHBaseConfiguration();
        HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
        try {
            //testCreateTable(hBaseAdmin);
            testGetTableDescribe(hBaseAdmin);
            //testDeleteTable(hBaseAdmin);
        } finally {
            hBaseAdmin.close();
        }
    }


    /**
     * 测试创建table
     * create 'users','f'
     * @throws IOException
     */
    static void testCreateTable(HBaseAdmin hbAdmin) throws IOException {
        TableName tableName = TableName.valueOf("users");   //表名

        if (!hbAdmin.tableExists(tableName)) {              // 判断表是否存在
            HTableDescriptor htd = new HTableDescriptor(tableName);
            htd.addFamily(new HColumnDescriptor("f"));  //Family 名称
            htd.setMaxFileSize(10000L);
            hbAdmin.createTable(htd);
            System.out.println("创建表成功!");
        } else {
            System.out.println("表存在了！");
        }
    }



    /**
     * 测试获取表信息
     * 'users', {TABLE_ATTRIBUTES => {MAX_FILESIZE => '10000'},
     *          {NAME => 'f', BLOOMFILTER => 'ROW', VERSIONS => '1', IN_MEMORY =>
     *          'false', KEEP_DELETED_CELLS => 'false', DATA_BLOCK_ENCODING =>
     *          'NONE', COMPRESSION => 'NONE', TTL => 'FOREVER',
     *           MIN_VERSIONS => '0', BLOCKCACHE => 'true', BLOCKSIZE => '65536',
     *           REPLICATION_SCOPE => '0'}
     * @param hbAdmin
     * @throws IOException
     */
    static void testGetTableDescribe(HBaseAdmin hbAdmin) throws IOException {
        TableName name = TableName.valueOf("users");
        if (hbAdmin.tableExists(name)) {// 判断表是否存在
            HTableDescriptor htd = hbAdmin.getTableDescriptor(name);
            System.out.println(htd);
        } else {
            System.out.println("表不存在！");
        }
    }

    /**
     * 测试删除
     *
     * @param hbAdmin
     * @throws IOException
     */
    static void testDeleteTable(HBaseAdmin hbAdmin) throws IOException {
        TableName name = TableName.valueOf("users");

        if (hbAdmin.tableExists(name)) {        // 判断表是否存在
            if (hbAdmin.isTableEnabled(name)) { // 判断表的状态是出于enabled还是disabled状态。
                hbAdmin.disableTable(name);
            }
            hbAdmin.deleteTable(name);
            System.out.println("删除成功！");
        } else {
            System.out.println("表不存在！");
        }
    }
}
