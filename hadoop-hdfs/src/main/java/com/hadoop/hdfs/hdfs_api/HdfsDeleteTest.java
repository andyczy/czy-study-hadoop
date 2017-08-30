package com.hadoop.hdfs.hdfs_api;

import org.apache.hadoop.conf.Configuration;
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


    //直接删除
    private static void deleteTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = getFileSystem();
        // true 递归删除
        boolean delete = fileSystem.delete(new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"),true);
        System.out.println("是否已经删除？" + delete);
        fileSystem.close();
    }

    //标记删除
    private static void deleteOnExitTest() throws Exception{
        // get filesystem
        FileSystem fileSystem = getFileSystem();
        boolean delete = fileSystem.deleteOnExit(new Path("/chenzy/mapreduce/wordcount/newMkdirs/test.txt"));
        System.out.println("是否已经删除？" + delete);
        fileSystem.close();
    }
}
