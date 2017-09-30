package com.czy.hadoop.service;


import com.czy.hadoop.model.PlatformDimension;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface PlatformDimensionDao {
    public PlatformDimension getPlatformDimension(PlatformDimension platform);

    public PlatformDimension getPlatformDimension(String platform);

    public PlatformDimension getPlatformDimension(int id);
}