package com.example.craily.controller.authority;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.po.Emp;
import com.example.craily.po.Menu;
import com.example.craily.po.Operations;
import com.example.craily.service.AuthorityService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.ResponeUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/authority")
public class AuthorityRestController {
	
	@Autowired
	private AuthorityService authorityService;

	@ApiOperation(value="根据登陆用户职位与下拉框所选职位以及所选菜单返回应显示菜单", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="queryMenu", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryMenu(@ModelAttribute("emp") Emp emp, String selectedJobNo, String selectedMenuNo) {
		return authorityService.queryMenu(emp, selectedJobNo, selectedMenuNo);
	}
	
	@ApiOperation(value="根据登陆用户职位与下拉框所选职位以及所选菜单返回应显示操作", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="queryOperations", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryOperations(@ModelAttribute("emp") Emp emp, String selectedJobNo, String selectedMenuNo) {
		return authorityService.queryOperations(emp, selectedJobNo, selectedMenuNo);
	}
	
	@ApiOperation(value="根据菜单ID查询菜单信息", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="queryMenuInfo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Menu> queryMenuInfo(@RequestParam("menuNo") String menuNo) {
		return authorityService.queryMenuInfo(menuNo);
	}
	
	@ApiOperation(value="创建菜单", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="createMenu", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> createMenu(@ModelAttribute("emp") Emp emp, Menu menu) {
		try {
			return authorityService.createMenu(emp, menu);
		} catch (Exception e) {
			return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), e.getMessage());
		}
	}
	
	@ApiOperation(value="编辑菜单", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="editMenu", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> editMenu(Menu menu) {
		try {
			return authorityService.editMenu(menu);
		} catch (Exception e) {
			return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), e.getMessage());
		}
	}
	
	@ApiOperation(value="根据权限NO查询权限", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="queryOperationInfo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Operations> queryOperationInfo(String operationsNo) {
		return authorityService.queryOperationInfo(operationsNo);
	}
	
	@ApiOperation(value="创建权限", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="createOperation", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> createOperation(Emp emp, Operations operations) {
		try {
			return authorityService.createOperation(emp, operations);
		} catch (Exception e) {
			return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), e.getMessage());
		}
	}
	
	@ApiOperation(value="编辑权限", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="editOperation", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> editOperation(Emp emp, Operations operations) {
		try {
			return authorityService.editOperation(emp, operations);
		} catch (Exception e) {
			return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), e.getMessage());
		}
	}
	
	@ApiOperation(value="保存用户权限", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="saveAuthority", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> saveAuthority(String selectedJobNo, String firstMenu, String[] secondMenuArray, String[] operationsNameArray) {
		try {
			return authorityService.saveAuthority(selectedJobNo, firstMenu, secondMenuArray, operationsNameArray);
		} catch (Exception e) {
			return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), e.getMessage());
		}
	}
	
	
	@ModelAttribute("emp")
	private Emp getSessionEmp() {
		Subject subject = SecurityUtils.getSubject();
		return (Emp) subject.getPrincipal();
	}
}
