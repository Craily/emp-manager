package com.example.craily.dao;

import com.example.craily.po.MenuRules;
import com.example.craily.po.MenuRulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRulesMapper {
    long countByExample(MenuRulesExample example);

    int deleteByExample(MenuRulesExample example);

    int insert(MenuRules record);

    int insertSelective(MenuRules record);

    List<MenuRules> selectByExample(MenuRulesExample example);

    int updateByExampleSelective(@Param("record") MenuRules record, @Param("example") MenuRulesExample example);

    int updateByExample(@Param("record") MenuRules record, @Param("example") MenuRulesExample example);
}