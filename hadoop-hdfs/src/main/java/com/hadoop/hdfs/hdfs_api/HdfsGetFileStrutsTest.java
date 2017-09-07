package com.hadoop.hdfs.hdfs_api;

import com.hadoop.hdfs.common.GetFileSystemClass;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

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


    //读取文件状态
    private static void fileStatusTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystem();
        FileStatus status = fileSystem.getFileStatus(new Path("/chenzy/mapreduce/wordcount/input/test.txt"));

        System.out.println("块大小：" + status.getBlockSize());
        System.out.println("路径："+status.getPath());
        System.out.println("时间："+ status.getAccessTime());
        System.out.println("副本："+ status.getReplication());
        fileSystem.close();
    }
}
