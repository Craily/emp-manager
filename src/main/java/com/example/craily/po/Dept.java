package com.example.craily.po;

public class Dept {
    private String deptNo;

    private String name;

    public Dept(String deptNo, String name) {
        this.deptNo = deptNo;
        this.name = name;
    }

    public Dept() {
        super();
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}