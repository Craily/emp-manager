package com.example.craily.controller.dept;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.po.Dept;
import com.example.craily.service.DeptService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dept")
public class DeptRestController {
	
	@Autowired
	private DeptService deptService;

	@ApiOperation(value="根据条件查询部门", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/queryDept", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryDept(PageUtil pageUtil, Dept dept) {
		return deptService.queryDept(pageUtil, dept);
	}
	
	@ApiOperation(value="创建部门", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/createDept", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> createDept(Dept dept) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = deptService.createDept(dept);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), e.getMessage());
		}
		return responeUtil;
	}
	
	@ApiOperation(value="编辑部门", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/editDept", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> editDept(Dept dept) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = deptService.editDept(dept);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Fail.getCode(), e.getMessage());
		}
		return responeUtil;
	}
	
}
