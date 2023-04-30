package com.iostar.beverageshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderItem implements Serializable {
    private Long Id;
    private String productName;
    private Long orderId;
    private List<String> toppingsName;
    private String sizeName;
    private Integer quantity;
    private BigDecimal totalPriceProduct;
    private BigDecimal totalPriceItem;
    private Integer status;
    private String imgProduct;

    public OrderItem(Long id, String productName, Long orderId, List<String> toppingsName, String sizeName, Integer quantity, BigDecimal totalPriceProduct, BigDecimal totalPriceItem, Integer status, String imgProduct) {
        Id = id;
        this.productName = productName;
        this.orderId = orderId;
        this.toppingsName = toppingsName;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.totalPriceProduct = totalPriceProduct;
        this.totalPriceItem = totalPriceItem;
        this.status = status;
        this.imgProduct = imgProduct;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<String> getToppingsName() {
        return toppingsName;
    }

    public void setToppingsName(List<String> toppingsName) {
        this.toppingsName = toppingsName;
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

    public BigDecimal getTotalPriceProduct() {
        return totalPriceProduct;
    }

    public void setTotalPriceProduct(BigDecimal totalPriceProduct) {
        this.totalPriceProduct = totalPriceProduct;
    }

    public BigDecimal getTotalPriceItem() {
        return totalPriceItem;
    }

    public void setTotalPriceItem(BigDecimal totalPriceItem) {
        this.totalPriceItem = totalPriceItem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }
}
