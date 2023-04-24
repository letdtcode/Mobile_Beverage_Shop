package com.iostar.beverageshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    private Long Id;
    private String nameCustomer;
    private String phoneNumber;
    private String address;
    private int shipping;
    private int payment;
    private BigDecimal totalItemPrice;
    private BigDecimal totalPrice;
    private String discountCode;
    private Long userId;
    private int status;

    public Order(Long id, String nameCustomer, String phoneNumber, String address, int shipping, int payment, BigDecimal totalItemPrice, BigDecimal totalPrice, String discountCode, Long userId, int status) {
        Id = id;
        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.shipping = shipping;
        this.payment = payment;
        this.totalItemPrice = totalItemPrice;
        this.totalPrice = totalPrice;
        this.discountCode = discountCode;
        this.userId = userId;
        this.status = status;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public BigDecimal getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(BigDecimal totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
