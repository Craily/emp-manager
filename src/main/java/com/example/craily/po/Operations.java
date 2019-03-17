package com.example.craily.po;

public class Operations {
    private String operationsno;

    private String name;

    private String code;

    private String menuno;

    public Operations(String operationsno, String name, String code, String menuno) {
        this.operationsno = operationsno;
        this.name = name;
        this.code = code;
        this.menuno = menuno;
    }

    public Operations() {
        super();
    }

    public String getOperationsno() {
        return operationsno;
    }

    public void setOperationsno(String operationsno) {
        this.operationsno = operationsno == null ? null : operationsno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMenuno() {
        return menuno;
    }

    public void setMenuno(String menuno) {
        this.menuno = menuno == null ? null : menuno.trim();
    }
}