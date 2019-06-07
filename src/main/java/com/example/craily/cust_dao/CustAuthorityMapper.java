package com.example.craily.cust_dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CustAuthorityMapper {

	/**
	 * 根据父级编号以及职位返回对应的菜单
	 * @param jobNo
	 * @return
	 */
	List<Map<String, Object>> queryMenu(@Param("parentMenuNo") String parentMenuNo, @Param("jobNo") String jobNo);
	
	/**
	 * 根据菜单以及职位编号查找对应的操作权限
	 * @param menuNo
	 * @param jobNo
	 * @return
	 */
	List<Map<String, Object>> queryOperations(@Param("menuNo") String menuNo, @Param("jobNo") String jobNo);
}
