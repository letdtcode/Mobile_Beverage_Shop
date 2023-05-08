package com.iostar.beverageshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private Long id;
    private String productName;
    private BigDecimal priceDefault;
    private String description;
    private Integer quantity;
    private String pathImage;
    private Long categoryId;
    private Float rating;

    public Product(Long id, String productName, BigDecimal priceDefault, String description, Integer quantity, String pathImage, Long categoryId, Float rating) {
        this.id = id;
        this.productName = productName;
        this.priceDefault = priceDefault;
        this.description = description;
        this.quantity = quantity;
        this.pathImage = pathImage;
        this.categoryId = categoryId;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPriceDefault() {
        return priceDefault;
    }

    public void setPriceDefault(BigDecimal priceDefault) {
        this.priceDefault = priceDefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
