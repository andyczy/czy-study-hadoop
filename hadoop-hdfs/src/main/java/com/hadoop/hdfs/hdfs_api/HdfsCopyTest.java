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
public class HdfsCopyTest {

    public static void main(String[] args) throws Exception{
        copyFromlocal();      //从本地拷贝到hdfs
        copyTolocal();       //从hdfs拷贝到本地
    }


    //从本地拷贝到hdfs
    private static void copyFromlocal() throws Exception{
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystem();
        fileSystem.copyFromLocalFile(new Path("E:\\from\\new.txt"),new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"));
        fileSystem.close();
    }

    //从hdfs拷贝到本地
    private static void copyTolocal() throws Exception{
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystem();
        fileSystem.copyToLocalFile(new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"),new Path("E:\\from\\new2.txt"));
        fileSystem.close();
    }
}
