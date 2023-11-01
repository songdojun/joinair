package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRODUCT {

    private int Pro_Code;       // 상품코드
    private int Cate_No;        // 카테고리식별번호
    private String Pro_Name;     // 상품이름
    private int Pro_Price;    // 가격
    private int Pro_Inventory; // 재고량
    private int Pro_Weight;   // 무게
}