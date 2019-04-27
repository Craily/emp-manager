package com.example.craily.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfiguration {

	/**
	 * 配置Filter
	 * @param securityManager
	 * @return
	 */
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//默认跳转到登陆页面
		shiroFilterFactoryBean.setLoginUrl("/login/index");
		//登陆成功后的页面
		shiroFilterFactoryBean.setSuccessUrl("/index/index");
		//没有权限的页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/login/index");
		
		//自定义过滤器
		Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
		shiroFilterFactoryBean.setFilters(filterMap);
		
		//权限控制
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//配置不会被拦截的地址，顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");
		filterChainDefinitionMap.put("/login/**", "anon");
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/loginout", "logout");
		
		//过滤链定义，从上向下顺序执行，一般将/**放在最为下边,不然会导致所有 url都被拦截
		//authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 配置核心安全事务管理器
	 * @param userRealm
	 * @return
	 */
	@Bean(name="securityManager")
	public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		//设置authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		defaultWebSecurityManager.setAuthenticator(authenticator);
		
		//设置authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		defaultWebSecurityManager.setAuthorizer(authorizer);
		
		//设置Realm
		defaultWebSecurityManager.setRealm(userRealm);
		return defaultWebSecurityManager;
	}
	
	@Bean(name="userRealm")
	public UserRealm userRealm() {
		return new UserRealm();
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean(name="shiroDialect")
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}
