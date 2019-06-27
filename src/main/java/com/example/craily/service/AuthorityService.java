package com.example.craily.service;

import java.util.Map;

import com.example.craily.po.Emp;
import com.example.craily.po.Menu;
import com.example.craily.po.Operations;
import com.example.craily.utils.ResponeUtil;

public interface AuthorityService {

	/**
	 * 根据登陆用户职位与下拉框所选职位以及所选菜单返回应显示菜单
	 * @param emp
	 * @param jobNo
	 * @return
	 */
	ResponeUtil<Map<String, Object>> queryMenu(Emp emp, String selectedJobNo, String selectedMenuNo);
	
	/**
	 * 根据登陆用户职位与下拉框所选职位以及所选菜单返回应显示操作
	 * @param emp
	 * @param jobNo
	 * @return
	 */
	ResponeUtil<Map<String, Object>> queryOperations(Emp emp, String selectedJobNo, String selectedMenuNo);
	
	/**
	 * 根据菜单ID查询菜单信息
	 * @param menuNo
	 * @return
	 */
	ResponeUtil<Menu> queryMenuInfo(String menuNo);
	
	/**
	 * 创建菜单
	 * @param menu
	 * @return
	 */
	ResponeUtil<String> createMenu(Emp emp, Menu menu);
	
	/**
	 * 编辑菜单
	 * @param menu
	 * @return
	 */
	ResponeUtil<String> editMenu(Menu menu);
	
	/**
	 * 根据权限NO查询权限
	 * @param operationsNo
	 * @return
	 */
	ResponeUtil<Operations> queryOperationInfo(String operationsNo);
	
	/**
	 * 创建权限
	 * @param emp
	 * @param operations
	 * @return
	 */
	ResponeUtil<String> createOperation(Emp emp, Operations operations);
	
	/**
	 * 编辑权限
	 * @param emp
	 * @param operations
	 * @return
	 */
	ResponeUtil<String> editOperation(Emp emp, Operations operations);
	
	/**
	 * 保存用户权限
	 * @param selectedJobNo
	 * @param firstMenu
	 * @param secondMenuArray
	 * @param operationsNameArray
	 * @return
	 */
	ResponeUtil<String> saveAuthority(String selectedJobNo, String firstMenu, String[] secondMenuArray, String[] operationsNameArray);
}
