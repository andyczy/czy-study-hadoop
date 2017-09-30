package com.czy.hadoop.service.impl;

import com.czy.hadoop.dao.DateDimensionDao;
import com.czy.hadoop.dao.DimensionDao;
import com.czy.hadoop.dao.KpiDimensionDao;
import com.czy.hadoop.dao.PlatformDimensionDao;
import com.czy.hadoop.model.KpiDimension;
import com.czy.hadoop.model.PlatformDimension;
import com.czy.hadoop.service.DimensionService;

import java.util.List;
import java.util.Map;

/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class DimensionServiceImpl implements DimensionService {
    private DimensionDao dimeDao;
    private DateDimensionDao dateDimeDao;
    private PlatformDimensionDao platformDimeDao;
    private KpiDimensionDao kpiDimeDao;

    public DimensionDao getDimeDao() {
        return dimeDao;
    }

    public void setDimeDao(DimensionDao dimeDao) {
        this.dimeDao = dimeDao;
    }

    public DateDimensionDao getDateDimeDao() {
        return dateDimeDao;
    }

    public void setDateDimeDao(DateDimensionDao dateDimeDao) {
        this.dateDimeDao = dateDimeDao;
    }

    public PlatformDimensionDao getPlatformDimeDao() {
        return platformDimeDao;
    }

    public void setPlatformDimeDao(PlatformDimensionDao platformDimeDao) {
        this.platformDimeDao = platformDimeDao;
    }

    public KpiDimensionDao getKpiDimeDao() {
        return kpiDimeDao;
    }

    public void setKpiDimeDao(KpiDimensionDao kpiDimeDao) {
        this.kpiDimeDao = kpiDimeDao;
    }

    @Override
    public List<Map<String, Object>> queryDimensionData(final Map<String, String> queryMap) {
        return this.dimeDao.queryDimensionData(queryMap);
    }

    @Override
    public PlatformDimension getPlatformDimension(final int dimensionPlatformId) {
        return this.platformDimeDao.getPlatformDimension(dimensionPlatformId);
    }

    @Override
    public PlatformDimension getPlatformDimension(final String platformName) {
        return this.platformDimeDao.getPlatformDimension(platformName);
    }

    @Override
    public Integer getDateDimensionId(final int year, final int season, final int month, final int week, final int day) {
        return this.dateDimeDao.getDateDimensionId(year, season, month, week, day);
    }

    @Override
    public Integer getKpiDimensionId(final String kpiName) {
        KpiDimension dimension = this.kpiDimeDao.getKpiDimension(kpiName);
        if (dimension != null) {
            return dimension.getId();
        }
        return null;
    }
}