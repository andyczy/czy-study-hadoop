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
public class HdfsDeleteTest {

    public static void main(String[] args) throws Exception{
        deleteTest();                //直接删除
        deleteOnExitTest();         //标记删除
    }



    //直接删除
    private static void deleteTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystem();
        // true 递归删除
        boolean delete = fileSystem.delete(new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"),true);
        System.out.println("是否已经删除？" + delete);
        fileSystem.close();
    }

    //标记删除
    private static void deleteOnExitTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystem();
        boolean delete = fileSystem.deleteOnExit(new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"));
        System.out.println("是否已经删除？" + delete);
        fileSystem.close();
    }
}
