package com.czy.hadoop.service;

import com.czy.hadoop.model.QueryModel;

import java.util.List;
import java.util.Map;




 /**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description: 处理ae基本数据交换的接口
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface AEService {
    public List<Map<String, Object>> execute(QueryModel queryModel) throws Exception;

}
