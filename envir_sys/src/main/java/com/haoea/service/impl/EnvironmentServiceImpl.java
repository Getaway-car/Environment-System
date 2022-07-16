package com.haoea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haoea.dao.EnvironmentDAO;
import com.haoea.domain.Environment;
import com.haoea.service.EnvironmentService;
import com.haoea.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    private EnvironmentDAO environmentDAO;

    @Override
    public IPage<Environment> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        LambdaQueryWrapper<Environment> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(Environment::getClock);
        environmentDAO.selectPage(page,lqw);
        return page;
    }

    /**
     * 根据日期查询
     * 数据库查询语句：select * from environment where clock like #{datetime};
     */
    @Override
    public IPage<Environment> getByDate(int currentPage, int pageSize, String dateTime) {
        IPage page = new Page(currentPage,pageSize);
        LambdaQueryWrapper<Environment> lqw = new LambdaQueryWrapper<>();
        lqw.like(Environment::getClock, dateTime).orderByAsc(Environment::getClock);
        environmentDAO.selectPage(page, lqw);
        return page;
    }

    @Override
    /**
        数据库查询语句：
        SELECT *, ABS(NOW() - clock)  AS diffTime
            FROM environment
            ORDER BY diffTime ASC
            LIMIT 0, 1;
     */
    public IPage<Environment> getLatest() {
        IPage page = new Page();
        Environment latest = environmentDAO.getLatest();
        ArrayList<Environment> list = new ArrayList<>();
        list.add(latest);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Double> getTem(String dateTime) {
        LambdaQueryWrapper<Environment> lqw = new LambdaQueryWrapper<>();
        //设置查询条件
        lqw.like(Environment::getClock, dateTime);
        //查询数据
        List<Environment> list = environmentDAO.selectList(lqw);
        //求出最大值，最小值和平均值
        Double maxTem = list.stream().mapToDouble(Environment::getTemperature).max().getAsDouble();
        Double minTem = list.stream().mapToDouble(Environment::getTemperature).min().getAsDouble();
        Double avgTem = list.stream().mapToDouble(Environment::getTemperature).average().getAsDouble();
        //平均值保留两位小数
        avgTem = (double) Math.round(avgTem * 100) / 100;
        //封装成list集合
        List<Double> temList = new ArrayList<>();
        temList.add(minTem);
        temList.add(maxTem);
        temList.add(avgTem);
        return temList;
    }

    @Override
    public List<Double> getHum(String dateTime) {
        LambdaQueryWrapper<Environment> lqw = new LambdaQueryWrapper<>();
        lqw.like(Environment::getClock, dateTime);
        List<Environment> list = environmentDAO.selectList(lqw);
        Double maxHum = list.stream().mapToDouble(Environment::getHumidity).max().getAsDouble();
        Double minHum = list.stream().mapToDouble(Environment::getHumidity).min().getAsDouble();
        Double avgHum = list.stream().mapToDouble(Environment::getHumidity).average().getAsDouble();
        avgHum = (double) Math.round(avgHum * 100) / 100;
        List<Double> humList = new ArrayList<>();
        humList.add(minHum);
        humList.add(maxHum);
        humList.add(avgHum);
        return humList;
    }

    @Override
    public List<Double> getLig(String dateTime) {
        LambdaQueryWrapper<Environment> lqw = new LambdaQueryWrapper<>();
        lqw.like(Environment::getClock, dateTime);
        List<Environment> list = environmentDAO.selectList(lqw);
        Double maxLig = list.stream().mapToDouble(Environment::getLight).max().getAsDouble();
        Double minLig = list.stream().mapToDouble(Environment::getLight).min().getAsDouble();
        Double avgLig = list.stream().mapToDouble(Environment::getLight).average().getAsDouble();
        avgLig = (double) Math.round(avgLig * 100) / 100;
        List<Double> ligList = new ArrayList<>();
        ligList.add(minLig);
        ligList.add(maxLig);
        ligList.add(avgLig);
        return ligList;
    }

    @Override
    public void scanTxt() {
        String path = "D:\\Desktop\\environment.txt";
        List<Environment> environmentList = Scanner.txtScan(path);
        for (Environment environment : environmentList) {
            environmentDAO.insert(environment);
        }
    }


}
