package com.czy.hadoop.service;

import com.czy.hadoop.model.QueryModel;

import java.util.List;
import java.util.Map;


/**
 * 处理ae基本数据交换的接口
 * 
 * @author gerry
 *
 */
public interface AEService {
    public List<Map<String, Object>> execute(QueryModel queryModel) throws Exception;

}
