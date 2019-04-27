package com.example.craily.po;

import java.util.Date;

public class Emp {
    private String empNo;

    private String name;

    private Date joinTime;

    private String sex;

    private String mobilePhone;

    private String deptNo;

    private String loginName;

    private String password;

    private String jobNo;

    public Emp(String empNo, String name, Date joinTime, String sex, String mobilePhone, String deptNo, String loginName, String password, String jobNo) {
        this.empNo = empNo;
        this.name = name;
        this.joinTime = joinTime;
        this.sex = sex;
        this.mobilePhone = mobilePhone;
        this.deptNo = deptNo;
        this.loginName = loginName;
        this.password = password;
        this.jobNo = jobNo;
    }

    public Emp() {
        super();
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }
}