package com.example.craily.po;

public class Menu {
    private String menuNo;

    private String name;

    private String url;

    private String parentMenuNo;

    private String openAllOperations;

    public Menu(String menuNo, String name, String url, String parentMenuNo, String openAllOperations) {
        this.menuNo = menuNo;
        this.name = name;
        this.url = url;
        this.parentMenuNo = parentMenuNo;
        this.openAllOperations = openAllOperations;
    }

    public Menu() {
        super();
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo == null ? null : menuNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParentMenuNo() {
        return parentMenuNo;
    }

    public void setParentMenuNo(String parentMenuNo) {
        this.parentMenuNo = parentMenuNo == null ? null : parentMenuNo.trim();
    }

    public String getOpenAllOperations() {
        return openAllOperations;
    }

    public void setOpenAllOperations(String openAllOperations) {
        this.openAllOperations = openAllOperations == null ? null : openAllOperations.trim();
    }
}