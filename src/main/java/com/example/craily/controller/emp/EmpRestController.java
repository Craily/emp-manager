package com.example.craily.controller.emp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.model.EmpBean;
import com.example.craily.po.Emp;
import com.example.craily.service.EmpService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

@RestController
@RequestMapping("/emp")
public class EmpRestController {
	
	@Autowired
	private EmpService empService;

	/**
	 * 根据条件查询员工
	 * @param pageUtil
	 * @param emp
	 * @return
	 */
	@PostMapping(value="/queryEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryEmp(PageUtil pageUtil, Emp emp) {
		return empService.queryEmp(pageUtil, emp);
	}
	
	/**
	 * 创建员工
	 * 默认创建一般员工
	 * @param empBean
	 * @return
	 */
	@PostMapping(value="/createEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> createEmp(EmpBean empBean) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = empService.createEmp(empBean);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), e.getMessage());
		}
		return responeUtil;
	}
}
