package com.example.craily.po;

public class OperationsRules {
    private String jobno;

    private String operationsno;

    public OperationsRules(String jobno, String operationsno) {
        this.jobno = jobno;
        this.operationsno = operationsno;
    }

    public OperationsRules() {
        super();
    }

    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno == null ? null : jobno.trim();
    }

    public String getOperationsno() {
        return operationsno;
    }

    public void setOperationsno(String operationsno) {
        this.operationsno = operationsno == null ? null : operationsno.trim();
    }
}