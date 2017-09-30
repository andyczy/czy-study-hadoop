package com.czy.hadoop.dao.impl;


import com.czy.hadoop.dao.PlatformDimensionDao;
import com.czy.hadoop.dao.mybatis.BaseDao;
import com.czy.hadoop.model.PlatformDimension;



/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class PlatformDimensionDaoImpl extends BaseDao implements PlatformDimensionDao {

    private static String modelClass = PlatformDimension.class.getName();
    private static String getPlatformDimension = modelClass + ".getPlatformDimension";

    @Override
    public PlatformDimension getPlatformDimension(PlatformDimension platform) {
        return this.getSqlSession().selectOne(getPlatformDimension, platform);
    }

    @Override
    public PlatformDimension getPlatformDimension(String platform) {
        PlatformDimension plat = new PlatformDimension(platform);
        return getPlatformDimension(plat);
    }

    @Override
    public PlatformDimension getPlatformDimension(int id) {
        PlatformDimension plat = new PlatformDimension(id);
        return getPlatformDimension(plat);
    }

}