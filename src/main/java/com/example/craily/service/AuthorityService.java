package com.example.craily.service;

import java.util.Map;

import com.example.craily.po.Emp;
import com.example.craily.po.Menu;
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
}
