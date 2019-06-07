package com.example.craily.controller.job;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/job")
public class JobViewController {

	@ApiOperation(value="职位汇总入口页面", httpMethod="GET", response=String.class)
	@GetMapping("/index")
	public String index() {
		return "job/index";
	}
	
	@ApiOperation(value="职位操作弹窗入口页面", httpMethod="GET", response=String.class, produces=MediaType.TEXT_PLAIN_VALUE)
	@GetMapping(value="/done", produces=MediaType.TEXT_PLAIN_VALUE)
	public String done(String jobNo, Model model) {
		model.addAttribute("jobNo", jobNo);
		return "job/done";
	}
}
