package com.foxconn.npbg.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UdtsDataBseDao {

    @Select("SELECT * FROM mfgvi_test_cell_config")
    @DS("genius")
    List<Object> findAll();
}
