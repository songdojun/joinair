package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ORDERS {
    private int Orders_Num;         // 주문번호
    private String User_Id;          // UserID
    private int Pro_Code;           // 상품코드
    private int Orders_Total_Price; // 총 주문금액
    private String Orders_State;     // 상태
    private int Orders_Total;    // 총주문수량




}
