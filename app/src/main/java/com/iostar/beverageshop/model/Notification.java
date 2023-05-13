package com.iostar.beverageshop.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String pathImgDescription;

    private Integer status;

    public Notification(String title, String content, Integer status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathImgDescription() {
        return pathImgDescription;
    }

    public void setPathImgDescription(String pathImgDescription) {
        this.pathImgDescription = pathImgDescription;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
