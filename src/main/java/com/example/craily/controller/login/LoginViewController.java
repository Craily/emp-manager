package com.example.craily.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/login")
public class LoginViewController {

	@ApiOperation(value="登陆入口页面", httpMethod="GET", response=String.class)
	@GetMapping("/index")
	public String index() {
		return "login/index";
	}
}
