package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class QNA {
    private int Qna_No;             // 상품문의번호
    private int Pro_Code;           // 상품코드
    private String User_Id;         // UserID
    private String Qna_Title;       // 문의제목
    private String Qna_Content;     // 문의내용 테스트

}
