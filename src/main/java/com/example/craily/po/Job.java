package com.example.craily.po;

public class Job {
    private String jobNo;

    private String name;

    private String level;

    private String deptNo;

    public Job(String jobNo, String name, String level, String deptNo) {
        this.jobNo = jobNo;
        this.name = name;
        this.level = level;
        this.deptNo = deptNo;
    }

    public Job() {
        super();
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
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

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }
}