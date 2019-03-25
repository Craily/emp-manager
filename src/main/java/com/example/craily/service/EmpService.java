package com.example.craily.service;

import java.util.Map;

import com.example.craily.po.Emp;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

public interface EmpService {

	/**
	 * 根据条件查询员工
	 * @param pageUtil
	 * @param emp
	 * @return
	 */
	ResponeUtil<Map<String, Object>> queryEmp(PageUtil pageUtil, Emp emp);
}
