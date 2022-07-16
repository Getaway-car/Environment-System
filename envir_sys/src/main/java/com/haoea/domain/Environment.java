package com.haoea.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Environment {

    private int id;
    //温度
    private double temperature;
    //湿度
    private double humidity;
    //光照强度
    private double light;
    //时间
    private Timestamp clock;
}
