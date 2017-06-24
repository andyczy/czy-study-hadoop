package com.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * @auther 陈郑游
 * @时间 2017/6/24 0024
 * @功能  去除引号
 * @问题
 * @博客地址：http://blog.csdn.net/javawebrookie
 * @GitHub地址：https://github.com/AndyCZY
 * @GitBook地址：https://www.gitbook.com/@chenzhengyou
 */
public class RemoveQuotesUDF extends UDF {


    /**
     * 1. Implement one or more methods named
     * "evaluate" which will be called by Hive.
     *
     * 2. "evaluate" should never be a void method.
     * However it can return "null" if needed.
     *
     */

    public Text evaluate(Text str){
        // validate
        if(null == str){
            return new Text() ;
        }
        if(null == str.toString()){
            return new Text() ;
        }
        // lower
        return new Text (str.toString().replaceAll("\"",""));
    }

    public static void main(String[] args) {
        System.out.println(new LowerUDF().evaluate(new Text("HIVE")));
    }
}
