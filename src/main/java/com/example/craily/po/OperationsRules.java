package com.example.craily.po;

public class OperationsRules {
    private String jobNo;

    private String operationsNo;

    public OperationsRules(String jobNo, String operationsNo) {
        this.jobNo = jobNo;
        this.operationsNo = operationsNo;
    }

    public OperationsRules() {
        super();
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }

    public String getOperationsNo() {
        return operationsNo;
    }

    public void setOperationsNo(String operationsNo) {
        this.operationsNo = operationsNo == null ? null : operationsNo.trim();
    }
}