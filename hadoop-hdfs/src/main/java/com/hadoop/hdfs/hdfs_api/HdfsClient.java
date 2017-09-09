package com.hadoop.hdfs.hdfs_api;

import com.hadoop.hdfs.common.GetFileSystemClass;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 陈郑游
 * @时间 2017/6/17 0017
 * @功能
 * @问题
 * @博客地址：http://blog.csdn.net/javawebrookie
 * @GitHub地址：https://github.com/AndyCZY
 * @GitBook地址：https://www.gitbook.com/@chenzhengyou
 */
public class HdfsClient {
    /**
     * Read Data
     * hadoop shell读取文件：bin/hdfs dfs -text /chenzy/mapreduce/wordcount/input/word.input
     *
     * @param fileName
     * @throws Exception
     */
    public static void read(String fileName) throws Exception {
        // get filesystem
        FileSystem fileSystem = GetFileSystemClass.getFileSystemDefautl();

        // read file
        Path path = new Path(fileName);

        // open file
        FSDataInputStream fsDataInputStream = fileSystem.open(path);

        try {
            // read
            IOUtils.copyBytes(fsDataInputStream, System.out, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close Stream
            IOUtils.closeStream(fsDataInputStream);
        }
    }


    /**
     * write file
     * SHELL ：bin/hdfs dfs -put -p wcinput/wc.input /chenzy/mapreduce/wordcount/input
     * 问题：
     *      1、Permission denied: user=administrator, access=WRITE, inode="/":root:supergroup:drwxr-xr-x
     *          解决：在hdfs的配置文件中，将dfs.permissions修改为false
     *      2、org.apache.Hadoop.dfs.SafeModeException: Cannot ... Name node is in safe mode
     *          解决：说明Hadoop的NameNode处在安全模式下。
     *          解除：bin/hadoop dfsadmin -safemode leave
     * */
    public static void write(String putFileName, String filename) throws Exception {
        // get filesystem
        FileSystem fileSystem =GetFileSystemClass.getFileSystemDefautl();

        // write path
        Path writePath = new Path(putFileName);

        // Output Stream
        FSDataOutputStream outStream = fileSystem.create(writePath);

        // file input Stream
        FileInputStream inStream = new FileInputStream(new File(filename));

        // stream read/write
        try {
            // read
            IOUtils.copyBytes(inStream, outStream, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close Stream
            IOUtils.closeStream(inStream);
            IOUtils.closeStream(outStream);
        }
    }

    // main
    public static void main(String[] args) throws Exception {

        // filename
        String filename = "/chenzy/mapreduce/wordcount/input/wc.input";
        read(filename);


        // write
        String putFileName = "/chenzy/mapreduce/wordcount/input/idea.input";
        // 本地E盘  "E:"+File.separatorChar+"nword.input";
        String file= "E:"+File.separatorChar+"nword.config";
        write(putFileName,file);

    }


}
