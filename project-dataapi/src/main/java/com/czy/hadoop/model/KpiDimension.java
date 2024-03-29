package com.czy.hadoop.model;



/**
 * @Auther 陈郑游
 * @Data 2017/9/30 0030
 * @Description:
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class KpiDimension {
    private int id;
    private String kpiName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public KpiDimension() {
        super();
    }

    public KpiDimension(int id) {
        super();
        this.id = id;
    }

    public KpiDimension(String kpiName) {
        super();
        this.kpiName = kpiName;
    }

    public KpiDimension(int id, String kpiName) {
        super();
        this.id = id;
        this.kpiName = kpiName;
    }
}