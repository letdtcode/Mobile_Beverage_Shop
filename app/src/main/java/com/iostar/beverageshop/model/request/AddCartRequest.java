package com.iostar.beverageshop.model.request;

import java.util.List;

public class AddCartRequest {
    private Long userId;
    private String productName;
    private Integer quantity;
    private List<String> toppingNameList;
    private String sizeName;

    public AddCartRequest(Long userId, String productName, Integer quantity, List<String> toppingNameList, String sizeName) {
        this.userId = userId;
        this.productName = productName;
        this.quantity = quantity;
        this.toppingNameList = toppingNameList;
        this.sizeName = sizeName;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getToppingNameList() {
        return toppingNameList;
    }

    public void setToppingNameList(List<String> toppingNameList) {
        this.toppingNameList = toppingNameList;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}
