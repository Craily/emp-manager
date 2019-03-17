package com.example.craily.dao;

import com.example.craily.po.OperationsRules;
import com.example.craily.po.OperationsRulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationsRulesMapper {
    long countByExample(OperationsRulesExample example);

    int deleteByExample(OperationsRulesExample example);

    int insert(OperationsRules record);

    int insertSelective(OperationsRules record);

    List<OperationsRules> selectByExample(OperationsRulesExample example);

    int updateByExampleSelective(@Param("record") OperationsRules record, @Param("example") OperationsRulesExample example);

    int updateByExample(@Param("record") OperationsRules record, @Param("example") OperationsRulesExample example);
}