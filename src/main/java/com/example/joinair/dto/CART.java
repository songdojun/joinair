package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CART {

    private int Cart_Code;   // 장바구니 번호
    private int Pro_Code;    // 상품코드
    private String User_Id;   // UserID
    private int Cart_Qua; // 수량
    private String Cart_Check; // 체크여부



}

