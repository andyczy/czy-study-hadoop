package com.czy.hadoop.dao.mybatis;

import com.czy.hadoop.model.QueryModel;

import java.util.List;
import java.util.Map;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class AEDao extends BaseDao {
    /**
     * 获得data数据，最终所有的请求具体的用户数据都是通过该接口来的，获取dimension除外
     * 
     * @param queryModel
     * @return
     */
    public List<Map<String, Object>> fetchMetricData(QueryModel queryModel) {
        return this.processWithNoCache(queryModel);
    }

    private List<Map<String, Object>> processWithNoCache(QueryModel queryModel) {
        return this.getSqlSession().selectList(queryModel.getQueryId(), queryModel);
    }
}