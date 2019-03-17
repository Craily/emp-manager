package com.example.craily.po;

public class Job {
    private String jobno;

    private String name;

    private String level;

    private String deptno;

    public Job(String jobno, String name, String level, String deptno) {
        this.jobno = jobno;
        this.name = name;
        this.level = level;
        this.deptno = deptno;
    }

    public Job() {
        super();
    }

    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno == null ? null : jobno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }
}