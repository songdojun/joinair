package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class NOTICE {
    private int Not_No;          // 공지사항 번호
    private String Not_Title;    // 공지사항 제목
    private String Not_Content;  // 공지사항 내용
    private Date Not_Date;       // 공지사항 날짜
    private String Not_Writer;   // 작성자
}
