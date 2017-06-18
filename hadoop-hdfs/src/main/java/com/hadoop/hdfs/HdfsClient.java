package com.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

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
     * Get FileSystem (得到系统文件)
     *
     * 问题：Could not locate executable null\bin\winutils.exe in the Hadoop binaries
     *      1、解决:解决方法很简单，配置环境变量，不想重启电脑可以在程序里加上
     * @return
     * @throws Exception
     */
    public static FileSystem getFileSystem() throws Exception {
        // 获取配置文件：core-site.xml,core-defautl.xml,hdfs-site.xml,hdfs-default.xml
        Configuration conf = new Configuration();

        //解决:解决方法很简单，配置环境变量，不想重启电脑可以在程序里加上
        System.setProperty("hadoop.home.dir", "F:\\ApplicationToolkit\\hadoopWinutils\\hadoop-common-2.2.0-bin-master");

        // get filesystem
        FileSystem fileSystem = FileSystem.get(conf);

        System.out.println(fileSystem);
        return fileSystem;
    }


    /**
     * Read Data
     * hadoop shell读取文件：bin/hdfs dfs -text /chenzhengyou/mapreduce/wordcount/input/word.input
     *
     * @param fileName
     * @throws Exception
     */
    public static void read(String fileName) throws Exception {

        // get filesystem
        FileSystem fileSystem = getFileSystem();

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
     * SHELL ：bin/hdfs dfs -put -p wcinput/wc.input /chenzhengyou/mapreduce/wordcount/input
     * 问题：
     *      1、Permission denied: user=administrator, access=WRITE, inode="/":root:supergroup:drwxr-xr-x
     *          解决：在hdfs的配置文件中，将dfs.permissions修改为false
     *      2、org.apache.Hadoop.dfs.SafeModeException: Cannot ... Name node is in safe mode
     *          解决：说明Hadoop的NameNode处在安全模式下。 解除：bin/hadoop dfsadmin -safemode leave
     * */
    public static void write(String putFileName, String filename) throws Exception {
        // get filesystem
        FileSystem fileSystem = getFileSystem();

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
        String filename = "/chenzhengyou/mapreduce/wordcount/input/putwc.input";
        read(filename);


        // write path "E:"+File.separatorChar+"nword.input";
        String putFileName = "/chenzhengyou/mapreduce/wordcount/input/idea.input";
        // 本地E盘
        String file= "E:"+File.separatorChar+"nword.input";;
        write(putFileName,file);
    }


}
