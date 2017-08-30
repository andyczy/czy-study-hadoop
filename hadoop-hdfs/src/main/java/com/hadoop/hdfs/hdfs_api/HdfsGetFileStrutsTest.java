package com.hadoop.hdfs.hdfs_api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Auther 陈郑游
 * @Data 2017/8/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class HdfsGetFileStrutsTest {

    public static void main(String[] args) throws Exception{
        fileStatusTest();        //读取文件状态
    }



    /**
     * Get FileSystem (得到系统文件)
     *
     * @return
     * @throws Exception
     */
    public static FileSystem getFileSystem() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fd.defaultFS","hdfs://chenzy-1:9000");
        // get filesystem
        FileSystem fileSystem = FileSystem.get(configuration);

        System.out.println(fileSystem);
        return fileSystem;
    }


    //读取文件状态
    private static void fileStatusTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = getFileSystem();
        FileStatus status = fileSystem.getFileStatus(new Path("/chenzy/mapreduce/wordcount/input/test.txt"));

        System.out.println("块大小：" + status.getBlockSize());
        System.out.println("路径："+status.getPath());
        System.out.println("时间："+ status.getAccessTime());
        System.out.println("副本："+ status.getReplication());
        fileSystem.close();
    }
}
