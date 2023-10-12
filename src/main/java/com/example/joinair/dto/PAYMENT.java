package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PAYMENT {


    private int payNo;
    private int ordersNum;
    private int payPrice;
    private String payMethod;
    private String payStatus;
    private String payRefund;
}
