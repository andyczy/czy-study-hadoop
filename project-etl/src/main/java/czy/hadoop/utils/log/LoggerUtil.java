package czy.hadoop.utils.log;


import czy.hadoop.common.Constant;
import czy.hadoop.utils.ip.IPSeekerExt;
import czy.hadoop.utils.ip.RegionInfo;
import czy.hadoop.utils.userAgent.UserAgentInfo_Model;
import czy.hadoop.utils.userAgent.UserAgent_Util;
import czy.hadoop.utils.util.TimeUtil;
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

    /**
     * 处理日志数据logText，返回处理结果map集合<br/>
     * 如果logText没有指定数据格式，那么直接返回empty的集合
     *
     * @param logText
     * @return
     */
    public static Map<String, String> handleLog(String logText) {
        Map<String, String> clientInfo = new HashMap<String, String>();

        if (StringUtils.isNotBlank(logText)) {
            String[] splits = logText.trim().split(Constant.LOG_SEPARTIOR);

            if (splits.length == 4) {
                // 日志格式为: ip^A服务器时间^Ahost^A请求参数
                clientInfo.put(Constant.LOG_COLUMN_NAME_IP, splits[0].trim()); // 设置ip
                // 设置服务器时间
                clientInfo.put(Constant.LOG_COLUMN_NAME_SERVER_TIME, String.valueOf(TimeUtil.parseNginxServerTime2Long(splits[1].trim())));
                int index = splits[3].indexOf("?");

                if (index > -1) {
                    // 获取请求参数，也就是我们的收集数据
                    String requestBody = splits[3].substring(index + 1);
                    // 处理请求参数
                    handleRequestBody(requestBody, clientInfo);
                    // 处理userAgent
                    handleUserAgent(clientInfo);
                    // 处理ip地址
                    handleIp(clientInfo);
                } else {
                    // 数据格式异常
                    clientInfo.clear();
                }
            }
        }
        return clientInfo;
    }

    /**
     * 处理ip地址
     *
     * @param clientInfo
     */
    private static void handleIp(Map<String,String> clientInfo) {
        if (clientInfo.containsKey(Constant.LOG_COLUMN_NAME_IP)) {
            String ip = clientInfo.get(Constant.LOG_COLUMN_NAME_IP);
            RegionInfo info = ipSeekerExt.analyticIp(ip);
            if (info != null) {
                clientInfo.put(Constant.LOG_COLUMN_NAME_COUNTRY, info.getCountry());
                clientInfo.put(Constant.LOG_COLUMN_NAME_PROVINCE, info.getProvince());
                clientInfo.put(Constant.LOG_COLUMN_NAME_CITY, info.getCity());
            }
        }
    }

    /**
     * 处理浏览器的userAgent信息
     *
     * @param clientInfo
     */
    private static void handleUserAgent(Map<String, String> clientInfo) {
        if (clientInfo.containsKey(Constant.LOG_COLUMN_NAME_USER_AGENT)) {
            UserAgentInfo_Model info = UserAgent_Util.analyticUserAgent(clientInfo.get(Constant.LOG_COLUMN_NAME_USER_AGENT));
            if (info != null) {
                clientInfo.put(Constant.LOG_COLUMN_NAME_OS_NAME, info.getOsName());
                clientInfo.put(Constant.LOG_COLUMN_NAME_OS_VERSION, info.getOsVersion());
                clientInfo.put(Constant.LOG_COLUMN_NAME_BROWSER_NAME, info.getBrowserName());
                clientInfo.put(Constant.LOG_COLUMN_NAME_BROWSER_VERSION, info.getBrowserVersion());
            }
        }
    }

    /**
     * 处理请求参数
     *
     * @param requestBody
     * @param clientInfo
     */
    private static void handleRequestBody(String requestBody, Map<String, String> clientInfo) {
        if (StringUtils.isNotBlank(requestBody)) {
            String[] requestParams = requestBody.split("&");
            for (String param : requestParams) {
                if (StringUtils.isNotBlank(param)) {
                    int index = param.indexOf("=");
                    if (index < 0) {
                        logger.warn("没法进行解析参数:" + param + "， 请求参数为:" + requestBody);
                        continue;
                    }

                    String key = null, value = null;
                    try {
                        key = param.substring(0, index);
                        value = URLDecoder.decode(param.substring(index + 1), "utf-8");
                    } catch (Exception e) {
                        logger.warn("解码操作出现异常", e);
                        continue;
                    }
                    if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                        clientInfo.put(key, value);
                    }
                }
            }
        }
    }


}
