package czy.hadoop.common;

/**
 * @Auther 陈郑游
 * @Data 2017/9/26 0026
 * @Description: 常量类
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class Constant {

    /**
     * 表名称
     */
    public static final String HBASE_NAME_EVENT_LOGS = "event_logs";

    /**
     * event_logs表的列簇名称
     */
    public static final String EVENT_LOGS_FAMILY_NAME = "info";

    /**
     * 日志分隔符
     */
    public static final String LOG_SEPARTIOR = "\\^A";

    /**
     * 用户ip地址
     */
    public static final String LOG_COLUMN_NAME_IP = "ip";

    /**
     * 服务器时间
     */
    public static final String LOG_COLUMN_NAME_SERVER_TIME = "s_time";

    /**
     * 事件名称
     */
    public static final String LOG_COLUMN_NAME_EVENT_NAME = "en";

    /**
     * 数据收集端的版本信息
     */
    public static final String LOG_COLUMN_NAME_VERSION = "ver";

    /**
     * 用户唯一标识符
     */
    public static final String LOG_COLUMN_NAME_UUID = "u_ud";

    /**
     * 会员唯一标识符
     */
    public static final String LOG_COLUMN_NAME_MEMBER_ID = "u_mid";

    /**
     * 会话id
     */
    public static final String LOG_COLUMN_NAME_SESSION_ID = "u_sd";
    /**
     * 客户端时间
     */
    public static final String LOG_COLUMN_NAME_CLIENT_TIME = "c_time";
    /**
     * 语言
     */
    public static final String LOG_COLUMN_NAME_LANGUAGE = "l";
    /**
     * 浏览器user agent参数
     */
    public static final String LOG_COLUMN_NAME_USER_AGENT = "b_iev";
    /**
     * 浏览器分辨率大小
     */
    public static final String LOG_COLUMN_NAME_RESOLUTION = "b_rst";
    /**
     * 当前url
     */
    public static final String LOG_COLUMN_NAME_CURRENT_URL = "p_url";
    /**
     * 前一个页面的url
     */
    public static final String LOG_COLUMN_NAME_REFERRER_URL = "p_ref";
    /**
     * 当前页面的title
     */
    public static final String LOG_COLUMN_NAME_TITLE = "tt";
    /**
     * 订单id
     */
    public static final String LOG_COLUMN_NAME_ORDER_ID = "oid";
    /**
     * 订单名称
     */
    public static final String LOG_COLUMN_NAME_ORDER_NAME = "on";
    /**
     * 订单金额
     */
    public static final String LOG_COLUMN_NAME_ORDER_CURRENCY_AMOUNT = "cua";
    /**
     * 订单货币类型
     */
    public static final String LOG_COLUMN_NAME_ORDER_CURRENCY_TYPE = "cut";
    /**
     * 订单支付金额
     */
    public static final String LOG_COLUMN_NAME_ORDER_PAYMENT_TYPE = "pt";
    /**
     * category名称
     */
    public static final String LOG_COLUMN_NAME_EVENT_CATEGORY = "ca";
    /**
     * action名称
     */
    public static final String LOG_COLUMN_NAME_EVENT_ACTION = "ac";
    /**
     * kv前缀
     */
    public static final String LOG_COLUMN_NAME_EVENT_KV_START = "kv_";
    /**
     * duration持续时间
     */
    public static final String LOG_COLUMN_NAME_EVENT_DURATION = "du";
    /**
     * 操作系统名称
     */
    public static final String LOG_COLUMN_NAME_OS_NAME = "os";
    /**
     * 操作系统版本
     */
    public static final String LOG_COLUMN_NAME_OS_VERSION = "os_v";
    /**
     * 浏览器名称
     */
    public static final String LOG_COLUMN_NAME_BROWSER_NAME = "browser";
    /**
     * 浏览器版本
     */
    public static final String LOG_COLUMN_NAME_BROWSER_VERSION = "browser_v";
    /**
     * ip地址解析的所属国家
     */
    public static final String LOG_COLUMN_NAME_COUNTRY = "country";
    /**
     * ip地址解析的所属省份
     */
    public static final String LOG_COLUMN_NAME_PROVINCE = "province";
    /**
     * ip地址解析的所属城市
     */
    public static final String LOG_COLUMN_NAME_CITY = "city";
}
