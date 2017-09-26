package czy.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

/**
 * @Auther 陈郑游
 * @Data 2017/9/26 0026
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class AnalyserLogDataRunnerMain {


    private static final Logger logger = Logger.getLogger(AnalyserLogDataRunner.class);

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Configuration(), new AnalyserLogDataRunner(), args);
        } catch (Exception e) {
            logger.error("执行日志解析job异常", e);
            throw new RuntimeException(e);
        }
    }

    //*******************************************************************************//

}
