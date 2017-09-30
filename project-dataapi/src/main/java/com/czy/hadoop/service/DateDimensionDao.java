package com.czy.hadoop.service;

import com.beifeng.ae.model.DateDimension;

public interface DateDimensionDao {
    public Integer getDateDimensionId(DateDimension date);

    public Integer getDateDimensionId(int year, int season, int month, int week, int day);
}