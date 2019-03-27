package com.example.craily.controller.emp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmpViewController {

	@RequestMapping("/index")
	public String index() {
		return "emp/index";
	}
	
	@RequestMapping("/done")
	public String done() {
		return "emp/done";
	}
}
