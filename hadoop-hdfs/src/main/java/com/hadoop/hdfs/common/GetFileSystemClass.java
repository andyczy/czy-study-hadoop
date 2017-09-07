package com.hadoop.hdfs.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

/**
 * @Auther 陈郑游
 * @Data 2017/9/7 0007
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class GetFileSystemClass {

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



    /**
     * Get FileSystem (得到系统文件)
     *
     * 问题：Could not locate executable null\bin\winutils.exe in the Hadoop binaries
     *      1、解决:解决方法很简单，配置环境变量，不想重启电脑可以在程序里加上
     * @return
     * @throws Exception
     */
    public static FileSystem getFileSystemDefautl() throws Exception {
        // 获取配置文件：core-site.xml,core-defautl.xml,hdfs-site.xml,hdfs-default.xml
        Configuration conf = new Configuration();

        //解决:解决方法很简单，配置环境变量，不想重启电脑可以在程序里加上
        System.setProperty("hadoop.home.dir", "F:\\ApplicationToolkit\\hadoopWinutils\\hadoop-common-2.2.0-bin-master");

        // get filesystem
        FileSystem fileSystem = FileSystem.get(conf);

        System.out.println(fileSystem);
        return fileSystem;
    }
}
