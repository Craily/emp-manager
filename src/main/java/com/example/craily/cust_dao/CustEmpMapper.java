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
 	
 	/**
 	 * 根据职位查找菜单权限
 	 * @param jobNo
 	 * @return
 	 */
 	List<Map<String, Object>> queryEmpJobMenuByJobNo(@Param("jobNo") String jobNo);
 	
 	/**
 	 * 根据职位查找操作权限
 	 * @param jobNo
 	 * @return
 	 */
 	List<Map<String, Object>> queryEmpOperationsByJobNo(@Param("jobNo") String jobNo);
 	
}