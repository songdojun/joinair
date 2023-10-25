package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SALES {
    private Date sales_Date;
    private double amount;
    private String Pro_Code;
    private String User_Id;
    private int ORDERS_NUM;
}