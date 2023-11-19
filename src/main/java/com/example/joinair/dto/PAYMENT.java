package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PAYMENT {
    private int PAY_NO;
    private int orderId;
    private int ORDERS_NUM;
    private int PAY_PRICE;
    private String PAY_METHOD;
    private String PAY_STATUS;

}
