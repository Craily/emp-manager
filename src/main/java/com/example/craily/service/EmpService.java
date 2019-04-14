package com.example.craily.service;

import java.util.Map;

import com.example.craily.model.EmpBean;
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
	
	/**
	 * 创建员工
	 * 默认创建一般员工
	 * @param empBean
	 * @return
	 */
	ResponeUtil<String> createEmp(EmpBean empBean);
	
	/**
	 * 编辑员工
	 * @param empBean
	 * @return
	 */
	ResponeUtil<String> editEmp(EmpBean empBean);
	
	/**
	 * 删除员工
	 * @param empNos
	 * @return
	 */
	ResponeUtil<String> delEmp(String[] empNos);
	
}
