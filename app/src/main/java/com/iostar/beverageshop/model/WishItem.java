package com.iostar.beverageshop.model;

import java.math.BigDecimal;

public class WishItem {
    private Long id;
    private String productName;
    private String categoryName;
    private BigDecimal priceProduct;
    private Float rating;
    private Long wishListId;
    private String pathImg;
    private int status;

    public WishItem(Long id, String productName, String categoryName, BigDecimal priceProduct, Float rating, Long wishListId, String pathImg, int status) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.priceProduct = priceProduct;
        this.rating = rating;
        this.wishListId = wishListId;
        this.pathImg = pathImg;
        this.status = status;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
