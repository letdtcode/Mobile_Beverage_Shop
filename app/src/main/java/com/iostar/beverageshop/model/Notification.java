package com.iostar.beverageshop.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private Long Id;

    private String title;

    private String content;

    private String pathImgDescription;

    public Notification(Long id, String title, String content, String pathImgDescription) {
        Id = id;
        this.title = title;
        this.content = content;
        this.pathImgDescription = pathImgDescription;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
