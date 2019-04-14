package com.example.craily.controller.emp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.model.EmpBean;
import com.example.craily.po.Dept;
import com.example.craily.po.Emp;
import com.example.craily.service.EmpService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/emp")
public class EmpRestController {
	
	@Autowired
	private EmpService empService;

	@ApiOperation(value="根据条件查询员工", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/queryEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryEmp(PageUtil pageUtil, Emp emp) {
		return empService.queryEmp(pageUtil, emp);
	}
	
	@ApiOperation(value="创建员工", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/createEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> createEmp(EmpBean empBean) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = empService.createEmp(empBean);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Error.getCode(), ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}
	
	@ApiOperation(value="编辑员工", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/editEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> editEmp(EmpBean empBean) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = empService.editEmp(empBean);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Error.getCode(), ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}
	
	@ApiOperation(value="删除员工", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/delEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> delEmp(String[] empNos) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = empService.delEmp(empNos);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Error.getCode(), ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}
	
}
