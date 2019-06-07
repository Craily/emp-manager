package com.example.craily.dao;

import com.example.craily.po.JobMenu;
import com.example.craily.po.JobMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMenuMapper {
    long countByExample(JobMenuExample example);

    int deleteByExample(JobMenuExample example);

    int insert(JobMenu record);

    int insertSelective(JobMenu record);

    List<JobMenu> selectByExample(JobMenuExample example);

    int updateByExampleSelective(@Param("record") JobMenu record, @Param("example") JobMenuExample example);

    int updateByExample(@Param("record") JobMenu record, @Param("example") JobMenuExample example);
}