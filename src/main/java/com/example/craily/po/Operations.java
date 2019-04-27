package com.example.craily.po;

public class Operations {
    private String operationsNo;

    private String name;

    private String menuNo;

    public Operations(String operationsNo, String name, String menuNo) {
        this.operationsNo = operationsNo;
        this.name = name;
        this.menuNo = menuNo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo == null ? null : menuNo.trim();
    }
}