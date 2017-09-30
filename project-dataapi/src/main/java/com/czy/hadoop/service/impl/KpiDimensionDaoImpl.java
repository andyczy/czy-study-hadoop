package com.czy.hadoop.service.impl;


import com.czy.hadoop.dao.KpiDimensionDao;
import com.czy.hadoop.dao.mybatis.BaseDao;
import com.czy.hadoop.model.KpiDimension;

/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class KpiDimensionDaoImpl extends BaseDao implements KpiDimensionDao {

    private static String modelClass = KpiDimension.class.getName();
    private static String getKpiDimension = modelClass + ".getKpiDimension";

    @Override
    public KpiDimension getKpiDimension(KpiDimension kpi) {
        return this.getSqlSession().selectOne(getKpiDimension, kpi);
    }

    @Override
    public KpiDimension getKpiDimension(String name) {
        KpiDimension kpi = new KpiDimension(name);
        return getKpiDimension(kpi);
    }

    @Override
    public KpiDimension getKpiDimension(int id) {
        KpiDimension kpi = new KpiDimension(id);
        return getKpiDimension(kpi);
    }

}