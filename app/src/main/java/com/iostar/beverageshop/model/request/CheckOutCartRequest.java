package com.iostar.beverageshop.model.request;

import java.util.List;

public class CheckOutCartRequest {
    private Long userId;
    private String address;
    private String nameCus;
    private int payMent;
    private String phoneNumber;
    private Integer shipping;
    private List<Long> cardItemId;

    public CheckOutCartRequest(Long userId, String address, String nameCus, int payMent, String phoneNumber, Integer shipping, List<Long> cardItemId) {
        this.userId = userId;
        this.address = address;
        this.nameCus = nameCus;
        this.payMent = payMent;
        this.phoneNumber = phoneNumber;
        this.shipping = shipping;
        this.cardItemId = cardItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public int getPayMent() {
        return payMent;
    }

    public void setPayMent(int payMent) {
        this.payMent = payMent;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public List<Long> getCardItemId() {
        return cardItemId;
    }

    public void setCardItemId(List<Long> cardItemId) {
        this.cardItemId = cardItemId;
    }
}
