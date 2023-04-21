package com.iostar.beverageshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CartItem implements Serializable {
    private Long Id;
    private String productName;
    private List<String> toppingName;
    private String sizeName;
    private Integer quantity;
    private BigDecimal totalPriceItem;

    public CartItem(Long id, String productName, List<String> toppingName, String sizeName, Integer quantity, BigDecimal totalPriceItem) {
        Id = id;
        this.productName = productName;
        this.toppingName = toppingName;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.totalPriceItem = totalPriceItem;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getToppingName() {
        return toppingName;
    }

    public void setToppingName(List<String> toppingName) {
        this.toppingName = toppingName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPriceItem() {
        return totalPriceItem;
    }

    public void setTotalPriceItem(BigDecimal totalPriceItem) {
        this.totalPriceItem = totalPriceItem;
    }
}
