package com.haoea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haoea.domain.Environment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface EnvironmentDAO extends BaseMapper<Environment> {

    /**
     * 查询某一天的数据
     * @return
     */
    List<Environment> getByDate(String datetime);

    /**
     * 查询最近的数据
     * @return
     */
    Environment getLatest();
}
