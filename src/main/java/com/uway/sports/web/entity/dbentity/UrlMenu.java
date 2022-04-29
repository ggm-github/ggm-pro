package com.uway.sports.web.entity.dbentity;

public class UrlMenu {

    private Integer id;
    private String url;
    private String url_name;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_name() {
        return url_name;
    }

    public void setUrl_name(String url_name) {
        this.url_name = url_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
