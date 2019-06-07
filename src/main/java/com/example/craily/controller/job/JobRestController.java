package com.example.craily.controller.job;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.po.Job;
import com.example.craily.service.JobService;
import com.example.craily.utils.ConstantUtil;
import com.example.craily.utils.PageUtil;
import com.example.craily.utils.ResponeUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/job")
public class JobRestController {
	
	@Autowired
	private JobService jobService;

	@ApiOperation(value="根据条件查询职位", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/queryJob", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<Map<String, Object>> queryJob(PageUtil pageUtil, Job job) {
		return jobService.queryJob(pageUtil, job);
	}
	
	@ApiOperation(value="创建职位", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/createJob", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> createJob(Job job) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = jobService.createJob(job);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Error.getCode(), ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}
	
	@ApiOperation(value="编辑职位", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/editJob", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> editJob(Job job) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = jobService.editJob(job);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Error.getCode(), ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}
	
	@ApiOperation(value="删除职位", httpMethod="POST", response=ResponeUtil.class, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(value="/delJob", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponeUtil<String> delJob(String[] jobNos) {
		ResponeUtil<String> responeUtil = null;
		try {
			responeUtil = jobService.delJob(jobNos);
		} catch (Exception e) {
			responeUtil = new ResponeUtil<>(ConstantUtil.Error.getCode(), ConstantUtil.Error.getMsg() + e.getMessage());
		}
		return responeUtil;
	}
}
