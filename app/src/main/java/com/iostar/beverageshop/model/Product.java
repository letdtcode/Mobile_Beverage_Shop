package com.iostar.beverageshop.model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private Long id;
    private String productName;
    private BigDecimal priceDefault;
    private String description;
    private Integer quantity;
    private Bitmap imageProduct;
    private Long categoryId;

    public Product(Long id, String productName, BigDecimal priceDefault, String description, Integer quantity, Bitmap imageProduct, Long categoryId) {
        this.id = id;
        this.productName = productName;
        this.priceDefault = priceDefault;
        this.description = description;
        this.quantity = quantity;
        this.imageProduct = imageProduct;
        this.categoryId = categoryId;
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

    public Bitmap getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(Bitmap imageProduct) {
        this.imageProduct = imageProduct;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
