package com.hadoop.hdfs.hdfs_api;

import com.hadoop.hdfs.common.GetFileSystemClass;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

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



    //创建目录
    private static void mkdirsTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystem();
        boolean mkdirs = fileSystem.mkdirs(new Path("/chenzy/mapreduce/wordcount/newMkdirs"));

        System.out.println(mkdirs);
        fileSystem.close();
    }
}
