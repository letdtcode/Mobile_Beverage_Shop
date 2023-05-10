package com.iostar.beverageshop.model;

import java.io.Serializable;

public class Category implements Serializable {
    private Long id;
    private String categoryName;
    private String description;
    private String pathImage;

    public Category(Long id, String categoryName, String description, String pathImg) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.pathImage = pathImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImg() {
        return pathImage;
    }

    public void setPathImg(String pathImg) {
        this.pathImage = pathImg;
    }
}
