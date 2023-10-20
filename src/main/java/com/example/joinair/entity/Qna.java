package com.example.joinair.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QNA")
public class Qna {

    @Id
    private int QNA_NO;           // 상품문의번호
    private int PRO_CODE;         // 상품코드
    private String USER_ID;       // UserID
    private String QNA_TITLE;     // 문의제목
    private String QNA_CONTENT;   // 문의내용
    private String QNA_COMMEND;   // 문의내용


}
