package com.haoea;

import com.haoea.dao.EnvironmentDAO;
import com.haoea.domain.Environment;
import com.haoea.util.Scanner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EnvirSysApplicationTests {

    @Autowired
    private EnvironmentDAO environmentDAO;

    @Test
    void testGetAll() {
        List<Environment> environmentList = environmentDAO.selectList(null);
        System.out.println(environmentList);
    }

    @Test
    void testScan(){
        String path = "D:\\Desktop\\environment.txt";
        List<Environment> environmentList = Scanner.txtScan(path);
        for (Environment environment : environmentList) {
            environmentDAO.insert(environment);
        }
    }

}
