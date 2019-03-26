package com.example.craily.service.impl;

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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public ResponeUtil<Map<String, Object>> queryDept(PageUtil pageUtil, Dept dept) {
		// TODO 根据条件查询部门
		ResponeUtil<Map<String, Object>> responeUtil = null;
		try {
			PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
			
			DeptExample deptExample = new DeptExample();
			Criteria criteria = deptExample.createCriteria();
			if(dept.getDeptNo() != null && !"".equals(dept.getDeptNo())) {
				criteria.andDeptNoLike("%" + dept.getDeptNo() + "%");
			}
			if(dept.getName() != null && !"".equals(dept.getName())) {
				criteria.andNameLike("%" + dept.getName() + "%");
			}
			List<Dept> deptList = deptMapper.selectByExample(deptExample);
			if(deptList == null || deptList.isEmpty()) {
				responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), ConstantUtil.empty.getMsg());
			}else {
				//获取分页信息
				PageInfo<Dept> pageInfo = new PageInfo<>(deptList);
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("count", pageInfo.getTotal());
				resultMap.put("list", deptList);
				responeUtil = new ResponeUtil<Map<String, Object>>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), e.getMessage());
		}
		return responeUtil;
	}

}
