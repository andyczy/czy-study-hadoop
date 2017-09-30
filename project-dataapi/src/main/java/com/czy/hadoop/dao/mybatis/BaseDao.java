package com.czy.hadoop.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;



/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public abstract class BaseDao {
    protected SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * 获得对应的mybatis session对象
     * 
     * @return
     */
    public SqlSession getSqlSession() {
        return this.sqlSessionTemplate;
    }
}
