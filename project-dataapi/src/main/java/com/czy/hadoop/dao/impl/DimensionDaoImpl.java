package com.czy.hadoop.dao.impl;

import com.czy.hadoop.dao.DimensionDao;
import com.czy.hadoop.dao.mybatis.BaseDao;

import java.util.List;
import java.util.Map;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class DimensionDaoImpl extends BaseDao implements DimensionDao {

    private static String nameSpace = DimensionDao.class.getName();
    private static String queryAllDimensionsId = ".queryDimensions";
    private static String queryAllDimensionsSql = nameSpace + queryAllDimensionsId;

    @Override
    public List<Map<String, Object>> queryDimensionData(Map<String, String> queryMap) {
        List<Map<String, Object>> list = this.getSqlSession().selectList(queryAllDimensionsSql, queryMap);
        return list;
    }

}
