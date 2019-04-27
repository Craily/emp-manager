package com.example.craily.controller.login;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.craily.po.Emp;
import com.example.craily.utils.ResponeUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
public class LoginRestController {
	
	

	@ApiOperation(value="登陆Action", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, HttpSession httpSession) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
		Subject subject = SecurityUtils.getSubject();
		String msg = "登陆成功";
		int flag = 1;
		try {
			subject.login(usernamePasswordToken);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "登陆失败";
			flag = 0;
		}
		return new ResponeUtil<String>(flag, msg);
	}
}
