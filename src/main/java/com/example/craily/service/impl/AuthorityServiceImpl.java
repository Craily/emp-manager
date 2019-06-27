package com.example.craily.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.craily.cust_dao.CustAuthorityMapper;
import com.example.craily.dao.JobMenuMapper;
import com.example.craily.dao.MenuMapper;
import com.example.craily.dao.OperationsMapper;
import com.example.craily.po.Emp;
import com.example.craily.po.JobMenu;
import com.example.craily.po.Menu;
import com.example.craily.po.MenuExample;
import com.example.craily.po.Operations;
import com.example.craily.po.OperationsExample;
import com.example.craily.service.AuthorityService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.ResponeUtil;
import com.example.craily.utils.UUIDUtil;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	private CustAuthorityMapper custAuthorityMapper;
	@Autowired
	private OperationsMapper operationsMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private JobMenuMapper jobMenuMapper;
	
	@Override
	public ResponeUtil<Map<String, Object>> queryMenu(Emp emp, String selectedJobNo, String selectedMenuNo) {
		// TODO 根据登陆用户职位与下拉框所选职位以及所选菜单返回应显示菜单
		ResponeUtil<Map<String, Object>> responeUtil = new ResponeUtil<Map<String,Object>>();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", new ArrayList<>());
		responeUtil.setStatus(ConstantUtil.Success.getCode());
		responeUtil.setMsg(ConstantUtil.Success.getMsg());
		responeUtil.setData(result);
		/**
		 * emp 根据自身职位返回所拥有的菜单
		 * jobNo 所选职位拥有的菜单
		 * 
		 * 所选职位拥有的菜单不能超出自身职位拥有的菜单
		 */
		List<Map<String, Object>> ownMenuList = custAuthorityMapper.queryMenu(selectedMenuNo, emp.getJobNo());
		if(ownMenuList == null || ownMenuList.isEmpty()) {
			responeUtil.setStatus(ConstantUtil.Empty.getCode());
			responeUtil.setMsg("自身没有菜单");
			return responeUtil;
		}
		List<Map<String, Object>> selectedMenuList = custAuthorityMapper.queryMenu(selectedMenuNo, selectedJobNo);
		
		if(selectedMenuList != null && !selectedMenuList.isEmpty()) {
			ownMenuList.forEach(ownMenu -> {
				//筛选出选中菜单
				selectedMenuList.forEach(selectedMenu -> {
					if(ownMenu.get("menu_no").equals(selectedMenu.get("menu_no"))) {
						ownMenu.put("LAY_CHECKED", true);
					}
				});
			});
		}
		result.put("list", ownMenuList);
		return responeUtil;
	}

	@Override
	public ResponeUtil<Map<String, Object>> queryOperations(Emp emp, String selectedJobNo, String selectedMenuNo) {
		// TODO 根据登陆用户职位与下拉框所选职位以及所选菜单返回应显示操作
		ResponeUtil<Map<String, Object>> responeUtil = new ResponeUtil<Map<String,Object>>();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", new ArrayList<>());
		responeUtil.setStatus(ConstantUtil.Success.getCode());
		responeUtil.setMsg(ConstantUtil.Success.getMsg());
		responeUtil.setData(result);
		
		//当前操作人对于该菜单拥有的操作
		OperationsExample ownOperationsExample = new OperationsExample();
		ownOperationsExample.createCriteria().andJobNoEqualTo(emp.getJobNo()).andMenuNoEqualTo(selectedMenuNo);
		List<Operations> ownOperationsList = operationsMapper.selectByExample(ownOperationsExample);
		//如果当前操作人为管理员并且没有操作权限，则默认插入CRUD
		if(ownOperationsList == null || ownOperationsList.isEmpty()) {
			if("1".equals(emp.getJobNo())) {
				try {
					ownOperationsList = ownOperationsList == null ? new ArrayList<Operations>() : ownOperationsList;
					ownOperationsList = retCRUDList(ownOperationsList, emp.getJobNo(), selectedMenuNo);
				} catch (Exception e) {
					// TODO: 捕获异常后不做处理，创建失败就失败，让前台页面手动操作。
				}
			}
		}
		
		//下拉框职位对于该菜单拥有的操作
		OperationsExample selectedOperationsExample = new OperationsExample();
		selectedOperationsExample.createCriteria().andJobNoEqualTo(selectedJobNo).andMenuNoEqualTo(selectedMenuNo);
		List<Operations> selectedOperationsList = operationsMapper.selectByExample(selectedOperationsExample);
		
		List<Map<String, String>> ownOperationsBeanList = new ArrayList<Map<String, String>>();
		//遍历当前操作人的操作权限是否包含下拉框职业的操作权限，包含则前台选中
		for (Operations ownOperations : ownOperationsList) {
			if(selectedOperationsList == null || selectedOperationsList.isEmpty()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("operationsNo", ownOperations.getOperationsNo());
				map.put("jobNo", ownOperations.getJobNo());
				map.put("menuNo", ownOperations.getMenuNo());
				map.put("operationsName", ownOperations.getOperationsName());
				ownOperationsBeanList.add(map);
			}else {
				for (Operations selectedOperations : selectedOperationsList) {
					if(ownOperations.getOperationsName().equals(selectedOperations.getOperationsName())) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("operationsNo", ownOperations.getOperationsNo());
						map.put("jobNo", ownOperations.getJobNo());
						map.put("menuNo", ownOperations.getMenuNo());
						map.put("operationsName", ownOperations.getOperationsName());
						map.put("LAY_CHECKED", "true");
						ownOperationsBeanList.add(map);
					}
				}
			}
		}
		
		result.put("list", ownOperationsBeanList);
		return responeUtil;
	}
	
	private List<Operations> retCRUDList(List<Operations> list, String jobNo, String menuNo){
		try {
			Operations create = crud(jobNo, menuNo, "新增");
			Operations del = crud(jobNo, menuNo, "删除");
			Operations update = crud(jobNo, menuNo, "更改");
			Operations read = crud(jobNo, menuNo, "查询");
			list.add(create);
			list.add(del);
			list.add(update);
			list.add(read);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private Operations crud(String jobNo, String menuNo, String operationName) {
		Operations operations = new Operations();
		operations.setJobNo(jobNo);
		operations.setMenuNo(menuNo);
		operations.setOperationsName(operationName);
		operations.setOperationsNo(UUIDUtil.get32UUID());
		operationsMapper.insert(operations);
		return operations;
	}

	
	@Override
	public ResponeUtil<Menu> queryMenuInfo(String menuNo) {
		// TODO 根据菜单ID查询菜单信息
		ResponeUtil<Menu> responeUtil = null;
		MenuExample menuExample = new MenuExample();
		menuExample.createCriteria().andMenuNoEqualTo(menuNo);
		List<Menu> menuList = menuMapper.selectByExample(menuExample);
		if(menuList == null || menuList.isEmpty()) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Empty.getCode(), ConstantUtil.Empty.getMsg());
		}else {
			responeUtil = new ResponeUtil<Menu>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), menuList.get(0));
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> createMenu(Emp emp, Menu menu) {
		// TODO 创建菜单
		try {
			ResponeUtil<String> responeUtil = null;
			menu.setMenuNo(UUIDUtil.get32UUID());
			int index = menuMapper.insertSelective(menu);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
			}else {
				//关联职位
				JobMenu jobMenu = new JobMenu();
				jobMenu.setJobNo(emp.getJobNo());
				jobMenu.setMenuNo(menu.getMenuNo());
				int i = jobMenuMapper.insert(jobMenu);
				if(i == 0) {
					responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
				}else {
					responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
				}
			}
			return responeUtil;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResponeUtil<String> editMenu(Menu menu) {
		// TODO 编辑菜单
		try {
			ResponeUtil<String> responeUtil = null;
			MenuExample menuExample = new MenuExample();
			menuExample.createCriteria().andMenuNoEqualTo(menu.getMenuNo());
			List<Menu> menuList = menuMapper.selectByExample(menuExample);
			if(menuList == null || menuList.isEmpty()) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Empty.getCode(), ConstantUtil.Empty.getMsg(), ConstantUtil.Empty.getMsg());
				return responeUtil;
			}

			Menu oldMenu = menuList.get(0);
			oldMenu.setName(menu.getName());
			oldMenu.setOpenAllOperations(menu.getOpenAllOperations());
			oldMenu.setParentMenuNo(menu.getParentMenuNo());
			int index = menuMapper.updateByExample(oldMenu, menuExample);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
			}else {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
			return responeUtil;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResponeUtil<Operations> queryOperationInfo(String operationsNo) {
		// 根据权限NO查询权限
		ResponeUtil<Operations> responeUtil = null;
		OperationsExample operationsExample = new OperationsExample();
		operationsExample.createCriteria().andOperationsNoEqualTo(operationsNo);
		List<Operations> list = operationsMapper.selectByExample(operationsExample);
		if(list == null || list.isEmpty()) {
			responeUtil = new ResponeUtil<Operations>(ConstantUtil.Empty.getCode(), ConstantUtil.Empty.getMsg());
			return responeUtil;
		}
		responeUtil = new ResponeUtil<Operations>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), list.get(0));
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> createOperation(Emp emp, Operations operations) {
		// TODO 创建权限
		try {
			operations.setJobNo(emp.getJobNo());
			operations.setOperationsNo(UUIDUtil.get32UUID());
			int index = operationsMapper.insert(operations);
			if(index == 0) {
				return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
			}else {
				return new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public ResponeUtil<String> editOperation(Emp emp, Operations operations) {
		// TODO 编辑权限
		try {
			OperationsExample operationsExample = new OperationsExample();
			operationsExample.createCriteria().andOperationsNoEqualTo(operations.getOperationsNo());
			List<Operations> list = operationsMapper.selectByExample(operationsExample);
			if(list == null || list.isEmpty()) {
				return new ResponeUtil<String>(ConstantUtil.Empty.getCode(), ConstantUtil.Empty.getMsg(), ConstantUtil.Empty.getMsg());
			}
			Operations oldOperations = list.get(0);
			oldOperations.setOperationsName(operations.getOperationsName());
			int index = operationsMapper.updateByExample(oldOperations, operationsExample);
			if(index == 0) {
				return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
			}else {
				return new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public ResponeUtil<String> saveAuthority(String selectedJobNo, String firstMenu, String[] secondMenuArray,
			String[] operationsNameArray) {
		// TODO 保存用户权限
		try {
			//保存菜单职位表
			JobMenu jobMenu = new JobMenu();
			jobMenu.setJobNo(selectedJobNo);
			jobMenu.setMenuNo(firstMenu);
			int i = jobMenuMapper.insert(jobMenu);
			if(i == 0) {
				return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
			}
			
			if(secondMenuArray.length == 0) {
				for (String operationName : operationsNameArray) {
					Operations operations = new Operations();
					operations.setJobNo(selectedJobNo);
					operations.setMenuNo(firstMenu);
					operations.setOperationsName(operationName);
					operations.setOperationsNo(UUIDUtil.get32UUID());
					int index = operationsMapper.insert(operations);
					if(index == 0) {
						return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
					}
				}
			}else {
				for (String secondMenu : secondMenuArray) {
					JobMenu menu = new JobMenu();
					menu.setJobNo(selectedJobNo);
					menu.setMenuNo(secondMenu);
					int in = jobMenuMapper.insert(menu);
					if(in == 0) {
						return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
					}
					
					for (String operationName : operationsNameArray) {
						Operations operations = new Operations();
						operations.setJobNo(selectedJobNo);
						operations.setMenuNo(secondMenu);
						operations.setOperationsName(operationName);
						operations.setOperationsNo(UUIDUtil.get32UUID());
						int index = operationsMapper.insert(operations);
						if(index == 0) {
							return new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg(), ConstantUtil.Fail.getMsg());
						}
					}
				}
			}
			return new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
