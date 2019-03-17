package com.example.craily.po;

public class MenuRules {
    private String jobno;

    private String menuno;

    private String openrules;

    public MenuRules(String jobno, String menuno, String openrules) {
        this.jobno = jobno;
        this.menuno = menuno;
        this.openrules = openrules;
    }

    public MenuRules() {
        super();
    }

    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno == null ? null : jobno.trim();
    }

    public String getMenuno() {
        return menuno;
    }

    public void setMenuno(String menuno) {
        this.menuno = menuno == null ? null : menuno.trim();
    }

    public String getOpenrules() {
        return openrules;
    }

    public void setOpenrules(String openrules) {
        this.openrules = openrules == null ? null : openrules.trim();
    }
}