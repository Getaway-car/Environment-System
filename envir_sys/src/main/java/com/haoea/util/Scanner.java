package com.haoea.util;

import com.haoea.domain.Environment;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    public static List<Environment> txtScan(String filePath) {
        List<Environment>  list = new ArrayList<Environment>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            //判断文件是否存在
            if (file.isFile() && file.exists()){
                //考虑到编码格式
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    Environment environment = new Environment();
                    //以‘,’作为分隔符
                    String[] s = lineTxt.split(",");
                    environment.setLight(Double.parseDouble(s[0]));
                    environment.setTemperature(Double.parseDouble(s[1]));
                    environment.setHumidity(Double.parseDouble(s[2]));
                    environment.setClock(new Timestamp(System.currentTimeMillis()));
                    list.add(environment);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }
}


