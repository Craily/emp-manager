package com.example.craily.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.craily.cust_dao.CustEmpMapper;
import com.example.craily.dao.EmpMapper;
import com.example.craily.po.Emp;
import com.example.craily.po.EmpExample;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private CustEmpMapper custEmpMapper;
	@Autowired
	private EmpMapper empMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO 权限验证
		String loginName = principalCollection.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		Emp emp = queryEmpByLoginName(loginName);
		if(emp == null) {
			throw new UnknownAccountException();
		}
		
		//设置角色
		List<Map<String, Object>> menus = custEmpMapper.queryEmpJobMenuByJobNo(emp.getJobNo());
		Set<String> roles = new HashSet<String>();
		menus.forEach(menu -> roles.add(menu.get("name").toString()));
		simpleAuthorizationInfo.setRoles(roles);
		//设置权限
		List<Map<String, Object>> operations = custEmpMapper.queryEmpOperationsByFlag(emp.getJobNo());
		Set<String> permissions = new HashSet<String>();
		operations.forEach(operation -> permissions.add(operation.get("name").toString()));
		simpleAuthorizationInfo.setStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		// TODO 身份验证
		String loginName = authenticationToken.getPrincipal().toString();

		Emp emp = queryEmpByLoginName(loginName);
		if(emp == null) {
			throw new UnknownAccountException();
		}
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(emp, emp.getPassword(), getName());
		return simpleAuthenticationInfo;
	}
	
	private Emp queryEmpByLoginName(String loginName) {
		EmpExample empExample = new EmpExample();
		empExample.createCriteria().andLoginNameEqualTo(loginName);
		List<Emp> emps = empMapper.selectByExample(empExample);
		if(emps == null || emps.isEmpty()) {
			return null;
		}
		return emps.get(0);
	}

}
