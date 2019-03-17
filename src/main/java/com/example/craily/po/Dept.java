package com.example.craily.po;

public class Dept {
    private String deptno;

    private String name;

    public Dept(String deptno, String name) {
        this.deptno = deptno;
        this.name = name;
    }

    public Dept() {
        super();
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}