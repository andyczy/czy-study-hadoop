package czy.hadoop.utils.ip;

/**
 * @Auther 陈郑游
 * @Data 2017/9/27 0027
 * @Description: ip地域相关的一个model
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public  class RegionInfo {

    public static final String DEFAULT_VALUE = "unknown"; // 默认值
    private String country = DEFAULT_VALUE; // 国家
    private String province = DEFAULT_VALUE; // 省份
    private String city = DEFAULT_VALUE; // 城市

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "RegionInfo [country=" + country + ", province=" + province + ", city=" + city + "]";
    }
}
