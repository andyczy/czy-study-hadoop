package com.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @auther 陈郑游
 * @时间 2017/6/24 0024
 * @功能 处理日期时间字段
 * @问题
 * @博客地址：http://blog.csdn.net/javawebrookie
 * @GitHub地址：https://github.com/AndyCZY
 * @GitBook地址：https://www.gitbook.com/@chenzhengyou
 */

@Description("描述：处理日期时间字段")
public class DateTransformUDF extends UDF {

    private final SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    private final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 原来的格式：31/Aug/2015:00:04:37 +0800
     *
     * 修改后的格式：20150831000437
     *
     * @param str
     * @return
     */
    public Text evaluate(Text input) {
        Text output = new Text();
        // validate
        if (null == input) {
            return null;
        }

        String inputDate = input.toString().trim();
        if(null == inputDate){
            return null ;
        }

        try{
            // parse
            Date parseDate = inputFormat.parse(inputDate);
            //transform
            String outputDate = outputFormat.format(parseDate);
            // set
            output.set(outputDate);

        }catch(Exception e){
            e.printStackTrace();
            return output ;
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(new DateTransformUDF().evaluate(new Text("31/Aug/2015:00:04:37 +0800")));
    }

}
