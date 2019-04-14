package com.example.craily.controller.emp;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/emp")
public class EmpViewController {

	@ApiOperation(value="员工汇总入口页面", httpMethod="GET", response=String.class)
	@RequestMapping("/index")
	public String index() {
		return "emp/index";
	}
	
	@ApiOperation(value="员工操作弹窗入口页面", httpMethod="GET", response=String.class, produces=MediaType.TEXT_PLAIN_VALUE)
	@RequestMapping("/done")
	public String done(String empNo, Model model) {
		model.addAttribute("empNo", empNo);
		return "emp/done";
	}
}
