package com.example.craily.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.craily.cust_dao.CustEmpMapper;
import com.example.craily.dao.EmpMapper;
import com.example.craily.model.EmpBean;
import com.example.craily.po.Emp;
import com.example.craily.service.EmpService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private CustEmpMapper custEmpMapper;
	@Autowired
	private EmpMapper empMapper;

	@Override
	public ResponeUtil<Map<String, Object>> queryEmp(PageUtil pageUtil, Emp emp) {
		// TODO 根据条件查询员工
		ResponeUtil<Map<String, Object>> responeUtil = null;
		try {
			PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
			
			List<Map<String, Object>> empList = custEmpMapper.queryEmp(emp);
			if(empList == null || empList.isEmpty()) {
				responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), ConstantUtil.empty.getMsg());
			}else {
				//获取分页信息
				PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(empList);
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("count", pageInfo.getTotal());
				resultMap.put("list", empList);
				responeUtil = new ResponeUtil<Map<String, Object>>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), e.getMessage());
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> createEmp(EmpBean empBean) {
		// TODO 创建员工
		ResponeUtil<String> responeUtil = null;
		try {
			empBean.setPassword("111111");
			empBean.setJobNo("3");
			
		} catch (Exception e) {
			e.printStackTrace();
			responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), e.getMessage());
		}
		return responeUtil;
	}

}
