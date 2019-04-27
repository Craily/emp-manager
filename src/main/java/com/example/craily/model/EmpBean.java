package com.example.craily.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2804968321261178960L;

	private String empNo;

	private String name;

	private Date joinTime;

	private String sex;

	private String mobilePhone;

	private String deptNo;

	private String loginName;

	private String password;

	private String jobNo;

	/**
	 * 
	 */
	public EmpBean() {
		super();
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) throws ParseException {
		this.joinTime = new SimpleDateFormat("yyyy-MM-dd").parse(joinTime);
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

}
