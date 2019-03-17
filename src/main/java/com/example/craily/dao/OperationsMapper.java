package com.example.craily.dao;

import com.example.craily.po.Operations;
import com.example.craily.po.OperationsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationsMapper {
    long countByExample(OperationsExample example);

    int deleteByExample(OperationsExample example);

    int insert(Operations record);

    int insertSelective(Operations record);

    List<Operations> selectByExample(OperationsExample example);

    int updateByExampleSelective(@Param("record") Operations record, @Param("example") OperationsExample example);

    int updateByExample(@Param("record") Operations record, @Param("example") OperationsExample example);
}