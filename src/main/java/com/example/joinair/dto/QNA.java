package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QNA {
    private int QNA_NO;           // 상품문의번호
    private int PRO_CODE;         // 상품코드
    private String USER_ID;       // UserID
    private String QNA_TITLE;     // 문의제목
    private String QNA_CONTENT;   // 문의내용
}
