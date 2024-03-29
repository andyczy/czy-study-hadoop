package com.czy.hadoop.service;

import com.czy.hadoop.model.PlatformDimension;

import java.util.List;
import java.util.Map;


/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public interface DimensionService {
    public List<Map<String, Object>> queryDimensionData(Map<String, String> queryMap);

    public PlatformDimension getPlatformDimension(int dimensionPlatformId);

    public PlatformDimension getPlatformDimension(String platformName);

    public Integer getKpiDimensionId(String kpiName);

    public Integer getDateDimensionId(int year, int season, int month, int week, int day);
    
//    public BrowserDimension getBrowserDimension(int browserId);
//    
//    public BrowserDimension getBrowserDimension(String browser, String browser_version);
//
//    public OsDimension getOsDimension(int dimensionOsId);
//
//    public OsDimension getOsDimension(String osName, String osVersion);
//
//    public LocationDimension getLocationDimension(int dimensionLocationId);
//
//    public LocationDimension getLocationDimension(String country, String province, String city);
//
//    public InboundDimension getInboundDimension(int dimensionInboundId);
//
//    public InboundDimension getInboundDimension(String name);
//
//    
//    public KeywordDimension getKeywordDimension(int keywordId);
//    
//    public KeywordDimension getKeywordDimension(String keyword);
}