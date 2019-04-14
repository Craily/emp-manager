package com.example.craily.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.craily.cust_dao.CustEmpMapper;
import com.example.craily.dao.EmpMapper;
import com.example.craily.model.EmpBean;
import com.example.craily.po.Emp;
import com.example.craily.po.EmpExample;
import com.example.craily.service.EmpService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;
import com.example.craily.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private CustEmpMapper custEmpMapper;
	@Autowired
	private EmpMapper empMapper;

	@Override
	public ResponeUtil<Map<String, Object>> queryEmp(PageUtil pageUtil, Emp emp) {
		// TODO 根据条件查询员工
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", 0);
		resultMap.put("list", new ArrayList<>());
		ResponeUtil<Map<String, Object>> responeUtil = new ResponeUtil<Map<String,Object>>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), resultMap);
		try {
			if(pageUtil.getPage() != 0) {
				PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
			}
			
			List<Map<String, Object>> empList = custEmpMapper.queryEmp(emp);
			if(empList == null || empList.isEmpty()) {
				responeUtil.setStatus(ConstantUtil.Empty.getCode());
				responeUtil.setMsg(ConstantUtil.Empty.getMsg());
			}else {
				//获取分页信息
				if(pageUtil.getPage() != 0) {
					PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(empList);
					resultMap.put("count", pageInfo.getTotal());
				}
				resultMap.put("list", empList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responeUtil.setStatus(ConstantUtil.Error.getCode());
			responeUtil.setMsg(ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> createEmp(EmpBean empBean) {
		// TODO 创建员工
		ResponeUtil<String> responeUtil = null;
		try {
			empBean.setPassword("111111");
			empBean.setJobNo("4");
			empBean.setEmpNo(UUIDUtil.get32UUID());
			
			Emp emp = new Emp();
			BeanUtils.copyProperties(empBean, emp);
			
			int index = empMapper.insert(emp);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), "新增操作失败");
			}else {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), "新增操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> editEmp(EmpBean empBean) {
		// TODO 编辑员工
		ResponeUtil<String> responeUtil = null;
		try {
			EmpExample empExample = new EmpExample();
			empExample.createCriteria().andEmpNoEqualTo(empBean.getEmpNo());
			List<Emp> emp = empMapper.selectByExample(empExample);
			if(emp == null || emp.isEmpty()) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Empty.getCode(), ConstantUtil.Empty.getMsg());
			}else {
				Emp sourceEmp = emp.get(0);
				BeanUtils.copyProperties(empBean, sourceEmp);
				
				int index = empMapper.updateByExampleSelective(sourceEmp, empExample);
				if(index == 0) {
					responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg());
				}else {
					responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> delEmp(String[] empNos) {
		// TODO 删除员工
		ResponeUtil<String> responeUtil = null;
		try {
			EmpExample empExample = new EmpExample();
			empExample.createCriteria().andEmpNoIn(Arrays.asList(empNos));
			int index = empMapper.deleteByExample(empExample);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg());
			}else {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responeUtil;
	}
	
}
