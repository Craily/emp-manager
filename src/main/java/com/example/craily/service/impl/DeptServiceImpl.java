package com.example.craily.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.craily.dao.DeptMapper;
import com.example.craily.po.Dept;
import com.example.craily.po.DeptExample;
import com.example.craily.po.DeptExample.Criteria;
import com.example.craily.service.DeptService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;
import com.example.craily.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public ResponeUtil<Map<String, Object>> queryDept(PageUtil pageUtil, Dept dept) {
		// TODO 根据条件查询部门
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", 0);
		resultMap.put("list", new ArrayList<>());
		ResponeUtil<Map<String, Object>> responeUtil = new ResponeUtil<Map<String,Object>>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), resultMap);
		try {
			if(pageUtil.getPage() != 0) {
				PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
			}
			
			DeptExample deptExample = new DeptExample();
			Criteria criteria = deptExample.createCriteria();
			if(dept.getDeptNo() != null && !"".equals(dept.getDeptNo())) {
				criteria.andDeptNoEqualTo(dept.getDeptNo());
			}
			if(dept.getName() != null && !"".equals(dept.getName())) {
				criteria.andNameLike("%" + dept.getName() + "%");
			}
			List<Dept> deptList = deptMapper.selectByExample(deptExample);
			if(deptList == null || deptList.isEmpty()) {
				responeUtil.setStatus(ConstantUtil.Empty.getCode());
				responeUtil.setMsg(ConstantUtil.Empty.getMsg());
			}else {
				if(pageUtil.getPage() != 0) {
					//获取分页信息
					PageInfo<Dept> pageInfo = new PageInfo<>(deptList);
					resultMap.put("count", pageInfo.getTotal());
				}
				resultMap.put("list", deptList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responeUtil.setStatus(ConstantUtil.Error.getCode());
			responeUtil.setMsg(ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> createDept(Dept dept) {
		// TODO 创建部门
		ResponeUtil<String> responeUtil = null;
		try {
			dept.setDeptNo(UUIDUtil.get32UUID());
			int index = deptMapper.insert(dept);
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

	@Override
	public ResponeUtil<String> editDept(Dept dept) {
		// TODO 编辑部门
		ResponeUtil<String> responeUtil = null;
		try {
			DeptExample deptExample = new DeptExample();
			deptExample.createCriteria().andDeptNoEqualTo(dept.getDeptNo());
			dept.setDeptNo(null);
			int index = deptMapper.updateByExampleSelective(dept, deptExample);
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

	@Override
	public ResponeUtil<String> delDept(String[] deptNos) {
		// TODO 删除部门
		ResponeUtil<String> responeUtil = null;
		try {
			DeptExample deptExample = new DeptExample();
			deptExample.createCriteria().andDeptNoIn(Arrays.asList(deptNos));
			int index = deptMapper.deleteByExample(deptExample);
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
