package com.example.craily.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.craily.dao.JobMapper;
import com.example.craily.po.Job;
import com.example.craily.po.JobExample;
import com.example.craily.po.JobExample.Criteria;
import com.example.craily.service.JobService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;
import com.example.craily.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobMapper jobMapper;

	@Override
	public ResponeUtil<Map<String, Object>> queryJob(PageUtil pageUtil, Job job) {
		// TODO 根据条件查询职位
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", 0);
		resultMap.put("list", new ArrayList<>());
		ResponeUtil<Map<String, Object>> responeUtil = new ResponeUtil<Map<String,Object>>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), resultMap);
		try {
			if(pageUtil.getPage() != 0) {
				PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
			}
			
			JobExample jobExample = new JobExample();
			Criteria criteria = jobExample.createCriteria();
			if(!StringUtils.isEmpty(job.getName())) {
				criteria.andNameLike("%" + job.getName() + "%");
			}
			if(!StringUtils.isEmpty(job.getJobNo())) {
				criteria.andJobNoEqualTo(job.getJobNo());
			}
			List<Job> jobList = jobMapper.selectByExample(jobExample);
			if(jobList == null || jobList.isEmpty()) {
				responeUtil.setStatus(ConstantUtil.Empty.getCode());
				responeUtil.setMsg(ConstantUtil.Empty.getMsg());
			}else {
				if(pageUtil.getPage() != 0) {
					//获取分页信息
					PageInfo<Job> pageInfo = new PageInfo<>(jobList);
					resultMap.put("count", pageInfo.getTotal());
				}
				resultMap.put("list", jobList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responeUtil.setStatus(ConstantUtil.Error.getCode());
			responeUtil.setMsg(ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> createJob(Job job) {
		// TODO 创建职位
		ResponeUtil<String> responeUtil = null;
		try {
			
			job.setJobNo(UUIDUtil.get32UUID());
			int index = jobMapper.insert(job);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg());
			}else {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> editJob(Job job) {
		// TODO 编辑职位
		ResponeUtil<String> responeUtil = null;
		try {
			JobExample jobExample = new JobExample();
			jobExample.createCriteria().andJobNoEqualTo(job.getJobNo());
			job.setJobNo(null);
			int index = jobMapper.updateByExampleSelective(job, jobExample);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg());
			}else {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responeUtil;
	}

	@Override
	public ResponeUtil<String> delJob(String[] jobNos) {
		// TODO 删除职位
		ResponeUtil<String> responeUtil = null;
		try {
			JobExample jobExample = new JobExample();
			jobExample.createCriteria().andJobNoIn(Arrays.asList(jobNos));
			int index = jobMapper.deleteByExample(jobExample);
			if(index == 0) {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Fail.getCode(), ConstantUtil.Fail.getMsg());
			}else {
				responeUtil = new ResponeUtil<String>(ConstantUtil.Success.getCode(), ConstantUtil.Success.getMsg(), ConstantUtil.Success.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responeUtil;
	}

}
