package com.example.craily.controller.dept;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.po.Dept;
import com.example.craily.service.DeptService;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

@RestController
@RequestMapping("/dept")
public class DeptRestController {
	
	@Autowired
	private DeptService deptService;

	/**
	 * 根据条件查询员工
	 * @param pageUtil
	 * @param emp
	 * @return
	 */
	@PostMapping(value="/queryDept", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryDept(PageUtil pageUtil, Dept dept) {
		return deptService.queryDept(pageUtil, dept);
	}
}
