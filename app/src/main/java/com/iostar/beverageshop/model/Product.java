package com.iostar.beverageshop.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String productName;
    private BigDecimal priceDefault;
    private String description;
    private Integer quantity;
    private Long categoryId;
    private String urlImgProduct;

    public Product(Long id, String productName, BigDecimal priceDefault, String description, Integer quantity, Long categoryId, String urlImgProduct) {
        this.id = id;
        this.productName = productName;
        this.priceDefault = priceDefault;
        this.description = description;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.urlImgProduct = urlImgProduct;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrlImgProduct() {
        return urlImgProduct;
    }

    public void setUrlImgProduct(String urlImgProduct) {
        this.urlImgProduct = urlImgProduct;
    }
}