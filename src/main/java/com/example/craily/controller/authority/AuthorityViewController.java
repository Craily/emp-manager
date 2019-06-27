package com.example.craily.controller.authority;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/authority")
public class AuthorityViewController {

	@ApiOperation(value="权限控制入口页面", httpMethod="GET", response=String.class)
	@GetMapping("/index")
	public String index() {
		return "authority/index";
	}
	
	@ApiOperation(value="菜单操作弹窗入口页面", httpMethod="GET", response=String.class, produces=MediaType.TEXT_PLAIN_VALUE)
	@RequestMapping("/mdone")
	public String mdone(String menuNo, String parentMenuNo, String selectedJobNo, Model model) {
		model.addAttribute("menuNo", menuNo);
		model.addAttribute("parentMenuNo", parentMenuNo);
		model.addAttribute("selectedJobNo", selectedJobNo);
		return "authority/mdone";
	}
	
	@ApiOperation(value="权限操作弹窗入口页面", httpMethod="GET", response=String.class, produces=MediaType.TEXT_PLAIN_VALUE)
	@RequestMapping("/odone")
	public String odone(String menuNo, String operationsNo, String selectedJobNo, Model model) {
		model.addAttribute("menuNo", menuNo);
		model.addAttribute("operationsNo", operationsNo);
		model.addAttribute("selectedJobNo", selectedJobNo);
		return "authority/odone";
	}
	
	
	
}
