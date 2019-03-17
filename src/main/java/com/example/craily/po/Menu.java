package com.example.craily.po;

public class Menu {
    private String menuno;

    private String name;

    private String url;

    private String parentmenurulesno;

    public Menu(String menuno, String name, String url, String parentmenurulesno) {
        this.menuno = menuno;
        this.name = name;
        this.url = url;
        this.parentmenurulesno = parentmenurulesno;
    }

    public Menu() {
        super();
    }

    public String getMenuno() {
        return menuno;
    }

    public void setMenuno(String menuno) {
        this.menuno = menuno == null ? null : menuno.trim();
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

    public String getParentmenurulesno() {
        return parentmenurulesno;
    }

    public void setParentmenurulesno(String parentmenurulesno) {
        this.parentmenurulesno = parentmenurulesno == null ? null : parentmenurulesno.trim();
    }
}