package com.czy.hadoop.dao.impl;


import com.czy.hadoop.dao.DateDimensionDao;
import com.czy.hadoop.dao.mybatis.BaseDao;
import com.czy.hadoop.model.DateDimension;



/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class DateDimensionDaoImpl extends BaseDao implements DateDimensionDao {

    private static String modelClass = DateDimension.class.getName();
    private static String getDateDimensionId = modelClass + ".getDateDimensionId";

    @Override
    public Integer getDateDimensionId(DateDimension date) {
        return this.getSqlSession().selectOne(getDateDimensionId, date);
    }

    @Override
    public Integer getDateDimensionId(int year, int season, int month, int week, int day) {
        DateDimension date = new DateDimension(year, season, month, week, day);
        return getDateDimensionId(date);
    }

}