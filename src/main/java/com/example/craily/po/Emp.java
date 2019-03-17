package com.example.craily.po;

import java.util.Date;

public class Emp {
    private String empno;

    private String name;

    private Date birthday;

    private String sex;

    private String mobilephone;

    private String deptno;

    private String password;

    public Emp(String empno, String name, Date birthday, String sex, String mobilephone, String deptno, String password) {
        this.empno = empno;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.mobilephone = mobilephone;
        this.deptno = deptno;
        this.password = password;
    }

    public Emp() {
        super();
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno == null ? null : empno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}