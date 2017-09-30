package com.czy.hadoop.dao;

import com.czy.hadoop.model.KpiDimension;



/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface KpiDimensionDao {
    public KpiDimension getKpiDimension(KpiDimension kpi);

    public KpiDimension getKpiDimension(String name);

    public KpiDimension getKpiDimension(int id);
}