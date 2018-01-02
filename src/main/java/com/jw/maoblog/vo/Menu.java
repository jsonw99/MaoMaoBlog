package com.jw.maoblog.vo;

/**
 * the menu for backend management.
 */
public class Menu {
    private String name; // the name of the function.
    private String url; // the url of the function.

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
