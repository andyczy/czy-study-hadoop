package com.czy.hadoop.dao;


import com.czy.hadoop.model.DateDimension;



/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface DateDimensionDao {
    public Integer getDateDimensionId(DateDimension date);

    public Integer getDateDimensionId(int year, int season, int month, int week, int day);
}