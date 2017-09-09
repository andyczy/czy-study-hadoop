package czy.hadoop.etl.log;

import cz.mallat.uasparser.UserAgentInfo;
import czy.hadoop.etl.common.EventLogConstants;
import czy.hadoop.etl.ip.IPSeekerExt;
import czy.hadoop.etl.userAgent.UserAgentUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


 /**
 * @Auther 陈郑游
 * @Data 2017/9/8 0008
 * @Description: 处理日志数据的具体工作类
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class);
    private static IPSeekerExt ipSeekerExt = new IPSeekerExt();


}
