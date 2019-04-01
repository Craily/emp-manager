package com.example.craily.po;

public class Dept {
    private String deptNo;

    private String name;

    private String address;

    public Dept(String deptNo, String name, String address) {
        this.deptNo = deptNo;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}