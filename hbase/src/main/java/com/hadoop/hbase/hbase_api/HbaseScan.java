package com.hadoop.hbase.hbase_api;

import com.hadoop.hbase.common.HbaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

/**
 * @Auther 陈郑游
 * @Data 2017/8/31 0031
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HbaseScan {

    public static void main(String[] args) throws Exception {
        Configuration conf = HbaseUtils.getHBaseConfiguration();
        testUseHTable(conf);
    }

    public static void testUseHTable(Configuration conf) throws IOException {
        HTable hTable = new HTable(conf, "users");
        try {

            testScan(hTable);
        } finally {
            hTable.close();
        }
    }

    /**
     * 测试scan
     *
     * @param hTable
     * @throws IOException
     */
    public static void testScan(HTableInterface hTable) throws IOException {
        Scan scan = new Scan();
        // 增加起始row key
        scan.setStartRow(Bytes.toBytes("row1"));
        scan.setStopRow(Bytes.toBytes("row5"));
        // 增加过滤filter
        FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        byte[][] prefixes = new byte[2][];
        prefixes[0] = Bytes.toBytes("id");
        prefixes[1] = Bytes.toBytes("name");
        MultipleColumnPrefixFilter mcpf = new MultipleColumnPrefixFilter(prefixes);
        list.addFilter(mcpf);
        scan.setFilter(list);

        ResultScanner rs = hTable.getScanner(scan);
        Iterator<Result> iter = rs.iterator();
        while (iter.hasNext()) {
            Result result = iter.next();
            printResult(result);
        }
    }

    /**
     * 打印result对象
     *
     * @param result
     */
    static void printResult(Result result) {
        System.out.println("*********************" + Bytes.toString(result.getRow()));
        NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result.getMap();
        for (Map.Entry<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> entry : map.entrySet()) {
            String family = Bytes.toString(entry.getKey());
            for (Map.Entry<byte[], NavigableMap<Long, byte[]>> columnEntry : entry.getValue().entrySet()) {
                String column = Bytes.toString(columnEntry.getKey());
                String value = "";
                if ("age".equals(column)) {
                    value = "" + Bytes.toInt(columnEntry.getValue().firstEntry().getValue());
                } else {
                    value = Bytes.toString(columnEntry.getValue().firstEntry().getValue());
                }
                System.out.println(family + ":" + column + ":" + value);
            }
        }
    }
}
