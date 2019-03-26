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
}
