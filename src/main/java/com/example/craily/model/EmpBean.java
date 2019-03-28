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

	private Date birthday;

	private String sex;

	private String mobilePhone;

	private String deptNo;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) throws ParseException {
		this.birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
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
