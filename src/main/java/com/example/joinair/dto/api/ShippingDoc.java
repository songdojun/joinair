package com.example.joinair.dto.api;

public class ShippingDoc {

    private String userId;
    private Integer orderId;


    public ShippingDoc() {
        super();
    }

    public ShippingDoc(String userId, Integer orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
