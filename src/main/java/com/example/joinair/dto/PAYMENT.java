package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PAYMENT {


    private int Pay_No;
    private int Orders_Num;
    private int Pay_Price;
    private String Pay_Method;
    private String Pay_Status;
    private String Pay_Refund;
}
