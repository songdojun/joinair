package com.example.joinair.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SHIPPING_TB")

public class Shipping {

    @Id
    @Column(name = "SHIPPING_ID")
    private Integer shippingId;

    @Column(name = "ADDRESS")
    private String address;

    public Shipping() {
        super();
    }

    public Shipping(Integer shippingId, String address) {
        this.shippingId = shippingId;
        this.address = address;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
