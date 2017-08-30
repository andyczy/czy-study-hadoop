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
public class HdfsCopyTest {

    public static void main(String[] args) throws Exception{
        copyFromlocal();    //从本地拷贝到hdfs
        copyTolocal();       //从hdfs拷贝到本地
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


    //从本地拷贝到hdfs
    private static void copyFromlocal() throws Exception{
        // get filesystem
        FileSystem fileSystem = getFileSystem();
        fileSystem.copyFromLocalFile(new Path("E:\\from\\new.txt"),new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"));
        fileSystem.close();
    }

    //从hdfs拷贝到本地
    private static void copyTolocal() throws Exception{
        // get filesystem
        FileSystem fileSystem = getFileSystem();
        fileSystem.copyToLocalFile(new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"),new Path("E:\\from\\new2.txt"));
        fileSystem.close();
    }
}
