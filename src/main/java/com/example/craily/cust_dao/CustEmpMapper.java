package com.example.craily.cust_dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.craily.po.Emp;

public interface CustEmpMapper {

	/**
	 * 根据条件查询员工
	 * @param emp
	 * @return
	 */
 	List<Map<String, Object>> queryEmp(@Param("emp") Emp emp);
 	
}