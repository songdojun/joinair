package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class DELIVERY {

    private int Del_No;         // 배송코드
    private int Pay_No;         // 결제번호
    private int D_Code;         // 드론식별코드
    private String Del_State;    // 상태
    private Date Del_Date;      // 출고날짜

}
//테스