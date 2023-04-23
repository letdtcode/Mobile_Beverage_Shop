package com.iostar.beverageshop.model.request;

public class AddWishRequest {
    private Long userId;
    private String productName;

    public AddWishRequest(Long userId, String productName) {
        this.userId = userId;
        this.productName = productName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
