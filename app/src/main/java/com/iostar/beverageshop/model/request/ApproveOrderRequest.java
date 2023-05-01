package com.iostar.beverageshop.model.request;

public class ApproveOrderRequest {
    private Long orderId;
    private Integer status;

    public ApproveOrderRequest(Long orderId, Integer status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
