package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class DELIVERY {

    private int Del_No;         // 배송코드
    private int D_Code;         // 드론식별코드
    private String Dp_Place; //배송 장소
    private String D_Phone; //주문자 연락처
    private String D_Pname; //주문자 성함

}
