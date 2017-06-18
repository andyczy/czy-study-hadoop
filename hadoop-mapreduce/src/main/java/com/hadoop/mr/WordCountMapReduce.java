package com.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @auther 陈郑游
 * @时间 2017/6/18 0018
 * @功能
 * @问题
 * @博客地址：http://blog.csdn.net/javawebrookie
 * @GitHub地址：https://github.com/AndyCZY
 * @GitBook地址：https://www.gitbook.com/@chenzhengyou
 */
public class WordCountMapReduce extends Configured implements Tool{




    /**
     * step 1: Map Class
     * <p>
     * map 输入 ——> map 输出
     * public class Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
     */
    public static class WordCountMapper extends
            Mapper<LongWritable, Text, Text, IntWritable> {


        private Text mapOutputKey = new Text();
        private final static IntWritable mapOuputValue = new  IntWritable(1);

        /**
         * Called once for each key/value pair in the input split. Most applications
         * should override this, but the default is the identity function.
         *
         * @param key
         * @param value
         * @param context
         */
        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            //super.map(key, value, context);
            // 获取值
            String lineValue = value.toString();

            // split 占内存 ——> 分割字符串
            // String[] strs = lineValue.split(" ");
            StringTokenizer stringTokenizer = new StringTokenizer(lineValue);

            // iterator
            while(stringTokenizer.hasMoreTokens()){
                // get word value
                String wordValue = stringTokenizer.nextToken();
                // set value
                mapOutputKey.set(wordValue);;
                // output  ——> 上下文操作
                context.write(mapOutputKey, mapOuputValue);
            }

        }

    }



    /**
     * step 2: Reduce Class
     *
     *
     * reduce 输入 ——> reduce 输出
     * public class Reducer<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
     */
    public static class WordCountReducer extends
            Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable outputValue = new  IntWritable();

        /**
         * This method is called once for each key. Most applications will define
         * their reduce class by overriding this method. The default implementation
         * is an identity function.
         *
         * @param key
         * @param values
         * @param context
         */
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            //super.reduce(key, values, context);

            // sum tmp
            int sum= 0 ;
            // iterator
            for(IntWritable value: values){
                // total
                sum += value.get();
            }
            // set value
            outputValue.set(sum);

            // output
            context.write(key, outputValue);
        }
    }


    // step 3: Driver ,component job
    // 封装map和reduce
    public int run(String[] args) throws Exception {
        // 1: get confifuration   ——> extends Configured ——> new Configuration();
        Configuration configuration = getConf();

        // 2: create Job
        Job job = Job.getInstance(configuration, this.getClass().getSimpleName());

        // run jar
        job.setJarByClass(this.getClass());


        // 3: set job
        // input  -> map  -> reduce -> output
        // 3.1: input ——> 参数(数据源)
        Path ipath = new Path(args[0]);
        FileInputFormat.addInputPath(job,ipath);

        // 3.2: map
        job.setMapperClass(WordCountMapper.class);
        // 设置输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 3.3: reduce
        job.setReducerClass(WordCountReducer.class);
        // 设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 3.4: output ——> 参数(输出源)
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        // 4: submit job ——> job 提交
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 0 :1;
    }



    // step 4: run program
    public static void main(String[] args) throws Exception {
        // 1: get confifuration
        Configuration configuration = new Configuration();

        //int status = new WordCountMapReduce().run(args);

        // ——> extends Configured ——> implements Tool
        int status = ToolRunner.run(configuration,//
                new WordCountMapReduce(),//
                args);

        System.exit(status);

    }

}
