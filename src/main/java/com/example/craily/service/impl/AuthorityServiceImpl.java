package com.example.craily.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.craily.cust_dao.CustAuthorityMapper;
import com.example.craily.dao.JobMenuMapper;
import com.example.craily.dao.MenuMapper;
import com.example.craily.dao.OperationsMapper;
import com.example.craily.model.OperationsBean;
import com.example.craily.po.Emp;
import com.example.craily.po.JobMenu;
import com.example.craily.po.Menu;
import com.example.craily.po.MenuExample;
import com.example.craily.po.Operations;
import com.example.craily.po.OperationsExample;
import com.example.craily.po.OperationsExample.Criteria;
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
	
	private String[] CRUD = {"新增", "查询", "更改", "删除"};
	
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
		result.put("CRUD", new ArrayList<>());
		result.put("others", new ArrayList<>());
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
		
		List<OperationsBean> ownOperationsBeanList = new ArrayList<OperationsBean>();
		List<OperationsBean> CRUDList = new ArrayList<OperationsBean>();
		//遍历当前操作人的操作权限是否包含下拉框职业的操作权限，包含则前台选中
		ownOperationsList.forEach(ownOperations -> {
			if(selectedOperationsList == null || selectedOperationsList.isEmpty()) {
				OperationsBean operationsBean = new OperationsBean();
				BeanUtils.copyProperties(ownOperations, operationsBean);
				ownOperationsBeanList.add(operationsBean);
			}else {
				//筛选出选中菜单
				selectedOperationsList.forEach(selectedOperations -> {
					if(ownOperations.getOperationsName().equals(selectedOperations.getOperationsName())) {
						OperationsBean operationsBean = new OperationsBean();
						BeanUtils.copyProperties(ownOperations, operationsBean);
						operationsBean.setLAY_CHECKED(true);
						ownOperationsBeanList.add(operationsBean);
					}
				});
			}
		});
		
		//分离CRUD与其他操作权限
		ownOperationsBeanList.forEach(ownOperationsBean -> {
			for (String name : CRUD) {
				if(name.equals(ownOperationsBean.getOperationsName())) {
					CRUDList.add(ownOperationsBean);
				}
			}
		});
		
		ownOperationsBeanList.removeAll(CRUDList);
		result.put("CRUD", CRUDList);
		result.put("others", ownOperationsBeanList);
		return responeUtil;
	}
	
	private List<Operations> retCRUDList(List<Operations> list, String jobNo, String menuNo){
		try {
			Operations create = crud(jobNo, menuNo, "新增");
			Operations del = crud(jobNo, menuNo, "删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除删除");
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
	
	
}
