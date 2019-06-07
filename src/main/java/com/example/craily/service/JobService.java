package com.example.craily.service;

import java.util.Map;

import com.example.craily.po.Job;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

public interface JobService {

	/**
	 * 根据条件查询职位
	 * @param pageUtil
	 * @param job
	 * @return
	 */
	ResponeUtil<Map<String, Object>> queryJob(PageUtil pageUtil, Job job);
	
	/**
	 * 创建职位
	 * @param job
	 * @return
	 */
	ResponeUtil<String> createJob(Job job);
	
	/**
	 * 编辑职位
	 * @param job
	 * @return
	 */
	ResponeUtil<String> editJob(Job job);
	
	/**
	 * 删除职位
	 * @param jobNos
	 * @return
	 */
	ResponeUtil<String> delJob(String[] jobNos);
}
