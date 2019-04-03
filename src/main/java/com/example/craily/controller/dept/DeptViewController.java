package com.example.craily.controller.dept;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/dept")
public class DeptViewController {

	@ApiOperation(value="部门汇总入口页面", httpMethod="GET", response=String.class)
	@GetMapping("/index")
	public String index() {
		return "dept/index";
	}
	
	@ApiOperation(value="部门操作弹窗入口页面", httpMethod="GET", response=String.class, produces=MediaType.TEXT_PLAIN_VALUE)
	@GetMapping(value="/done", produces=MediaType.TEXT_PLAIN_VALUE)
	public String done(String deptNo, Model model) {
		model.addAttribute("deptNo", deptNo);
		return "dept/done";
	}
}
