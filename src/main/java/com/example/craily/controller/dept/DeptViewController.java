package com.example.craily.controller.dept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dept")
public class DeptViewController {

	@RequestMapping("/index")
	public String index() {
		return "dept/index";
	}
	@RequestMapping("/done")
	public String done() {
		return "dept/done";
	}
}
