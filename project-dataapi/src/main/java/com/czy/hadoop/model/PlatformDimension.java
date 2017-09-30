package com.czy.hadoop.model;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description: 返回结果
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class PlatformDimension {
    private int id;
    private String platform;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public PlatformDimension() {
        super();
    }

    public PlatformDimension(int id) {
        super();
        this.id = id;
    }

    public PlatformDimension(String platform) {
        super();
        this.platform = platform;
    }

    public PlatformDimension(int id, String platform) {
        super();
        this.id = id;
        this.platform = platform;
    }

}
