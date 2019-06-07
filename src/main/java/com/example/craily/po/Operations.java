package com.example.craily.po;

public class Operations {
    private String operationsNo;

    private String jobNo;

    private String menuNo;

    private String operationsName;

    public Operations(String operationsNo, String jobNo, String menuNo, String operationsName) {
        this.operationsNo = operationsNo;
        this.jobNo = jobNo;
        this.menuNo = menuNo;
        this.operationsName = operationsName;
    }

    public Operations() {
        super();
    }

    public String getOperationsNo() {
        return operationsNo;
    }

    public void setOperationsNo(String operationsNo) {
        this.operationsNo = operationsNo == null ? null : operationsNo.trim();
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

    public String getOperationsName() {
        return operationsName;
    }

    public void setOperationsName(String operationsName) {
        this.operationsName = operationsName == null ? null : operationsName.trim();
    }
}