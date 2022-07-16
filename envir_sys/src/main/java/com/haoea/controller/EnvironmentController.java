package com.haoea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haoea.common.Code;
import com.haoea.common.Result;
import com.haoea.domain.Environment;
import com.haoea.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Haoea
 */
@RestController
@RequestMapping("/environments")
public class EnvironmentController {

    @Autowired
    private EnvironmentService environmentService;

    /**
     * 获取某一天的环境数据
     */
    @GetMapping("/{currentPage}/{pageSize}/{datetime}")
    public Result getByDate(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @PathVariable String datetime){
        String queryDate = datetime + "%";
        IPage<Environment> environmentList = environmentService.getByDate(currentPage,pageSize,queryDate);
        Integer code = environmentList != null ? Code.OK : Code.ERROR;
        String msg = environmentList != null ? "" : "数据查询异常！";
        return new Result(code, environmentList, msg);
    }

    /**
     * 默认获取当前最新的环境数据
     */
    @GetMapping
    public Result getLatest(){
        IPage<Environment> latest = environmentService.getLatest();
        Integer code = latest != null ? Code.OK : Code.ERROR;
        String msg = latest != null ? "" : "数据查询异常！";
        return new Result(code, latest, msg);
    }

    /**
     * 获取所有数据
     */
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getAll(@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        IPage<Environment> pageEnvir = environmentService.getPage(currentPage, pageSize);
        Integer code = pageEnvir != null ? Code.OK : Code.ERROR;
        String msg = pageEnvir != null ? "" : "数据查询异常！";
        return new Result(code, pageEnvir, msg);
    }

    /**
     * 获取某一天的温度的最大值，最小值和平均值
     */
    @GetMapping("/Tem/{datetime}")
    public Result getTem(@PathVariable String datetime){
        List<Double> tem = environmentService.getTem(datetime);
        Integer code = tem != null ? Code.OK : Code.ERROR;
        String msg = tem != null ? "" : "出错了！";
        return new Result(code, tem, msg);
    }

    /**
     * 获取某一天的湿度的最大值，最小值和平均值
     */
    @GetMapping("/Hum/{datetime}")
    public Result getHum(@PathVariable String datetime){
        List<Double> hum = environmentService.getHum(datetime);
        Integer code = hum != null ? Code.OK : Code.ERROR;
        String msg = hum != null ? "" : "出错了！";
        return new Result(code, hum, msg);
    }

    /**
     * 获取某一天的光照的最大值，最小值和平均值
     */
    @GetMapping("/Lig/{datetime}")
    public Result getLig(@PathVariable String datetime){
        List<Double> lig = environmentService.getLig(datetime);
        Integer code = lig != null ? Code.OK : Code.ERROR;
        String msg = lig != null ? "" : "出错了！";
        return new Result(code, lig, msg);
    }

    /**
     * 把本地文本读入数据库
     */
    @GetMapping("/scan")
    public void ScanTxt(){
        environmentService.scanTxt();
    }

}
