package com.example.craily.aop;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.craily.po.Menu;

@Aspect
@Component
public class ParticipationAspect {

	@Pointcut("execution(public * com.example.craily.controller..*.*(..))")
	public void excuteService() {}
	
	@Before(value="excuteService()")
	public void doBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			if(object instanceof Menu) {
				Menu menu = (Menu) object;
				if(StringUtils.isEmpty(menu.getParentMenuNo()) || "null".equals(menu.getParentMenuNo())) {
					menu.setParentMenuNo(null);
				}
			}
		}
	}
}
