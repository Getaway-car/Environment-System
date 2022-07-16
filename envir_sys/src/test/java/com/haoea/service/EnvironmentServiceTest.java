package com.haoea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haoea.domain.Environment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class EnvironmentServiceTest {
    @Autowired
    private EnvironmentService environmentService;

    @Test
    void testGetALLPage(){
        IPage<Environment> page = environmentService.getPage(1, 10);
        System.out.println(page.getRecords());
    }

    @Test
    void testGetDayPage(){
//        List<Environment> day = environmentService.getByDate("2022-05-03");
//        System.out.println(day);
    }

    @Test
    void testGetLatest(){
        IPage<Environment> latest = environmentService.getLatest();
        System.out.println(latest.getRecords());
    }

    @Test
    void testGetHum(){
        List<Double> hum = environmentService.getHum("2022-05-03");
        System.out.println(hum);
    }

    @Test
    void testGetTem(){
        List<Double> tem = environmentService.getTem("2022-05-03");
        System.out.println(tem);
    }
    @Test
    void testGetLig(){
        List<Double> lig = environmentService.getLig("2022-05-03");
        System.out.println(lig);
    }

}
