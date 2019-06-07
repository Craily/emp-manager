package com.example.craily.po;

public class JobMenu {
    private String jobNo;

    private String menuNo;

    public JobMenu(String jobNo, String menuNo) {
        this.jobNo = jobNo;
        this.menuNo = menuNo;
    }

    public JobMenu() {
        super();
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo == null ? null : menuNo.trim();
    }
}