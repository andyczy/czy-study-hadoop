package com.hadoop.hdfs.hdfs_api;

import org.apache.hadoop.conf.Configuration;
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
public class HdfsMkdirsTest {

    public static void main(String[] args) throws Exception{
        mkdirsTest();    //创建目录
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


    //创建目录
    private static void mkdirsTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = getFileSystem();
        boolean mkdirs = fileSystem.mkdirs(new Path("/chenzy/mapreduce/wordcount/newMkdirs"));

        System.out.println(mkdirs);
        fileSystem.close();
    }
}
