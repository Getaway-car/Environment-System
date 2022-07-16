package com.haoea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haoea.domain.Environment;

import java.util.List;

/**
 * @author Haoea
 */
public interface EnvironmentService {

    IPage<Environment> getPage(int currentPage, int pageSize);

    /**
     * 查询某一天的数据
     * @return
     */
    IPage<Environment> getByDate(int currentPage, int pageSize, String dateTime);

    /**
     * 查询最新的数据
     * @return
     */
    IPage<Environment>  getLatest();

    /**
     *  计算某一天温度的平均值、最大值、最小值
     */
    List<Double> getTem(String dateTime);
    List<Double> getHum(String dateTime);
    List<Double> getLig(String dateTime);

    void scanTxt();

}
