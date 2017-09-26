package czy.hadoop.utils.userAgent;

/**
 * @Auther 陈郑游
 * @Data 2017/9/26 0026
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class UserAgentInfo {

    /**
     * 内部解析后的浏览器信息model对象
     *
     * @author gerry
     */
    private String browserName; // 浏览器名称
    private String browserVersion; // 浏览器版本号
    private String osName; // 操作系统名称
    private String osVersion; // 操作系统版本号

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Override
    public String toString() {
        return "UserAgentInfo [browserName=" + browserName + ", browserVersion=" + browserVersion + ", osName="
                + osName + ", osVersion=" + osVersion + "]";
    }
}
