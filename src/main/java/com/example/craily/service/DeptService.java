package com.example.craily.service;

import java.util.Map;

import com.example.craily.po.Dept;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

public interface DeptService {

	/**
	 * 根据条件查询部门
	 * @param pageUtil
	 * @param dept
	 * @return
	 */
	ResponeUtil<Map<String, Object>> queryDept(PageUtil pageUtil, Dept dept);
	
	/**
	 * 创建部门
	 * @param dept
	 * @return
	 */
	ResponeUtil<String> createDept(Dept dept);
	
	/**
	 * 编辑部门
	 * @param dept
	 * @return
	 */
	ResponeUtil<String> editDept(Dept dept);
	
	/**
	 * 删除部门
	 * @param dept
	 * @return
	 */
	ResponeUtil<String> delDept(String[] deptNos);
}
