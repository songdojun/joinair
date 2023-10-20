package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ANSWER {
    private int Ans_No;
    private int Qna_No;
    private String Ans_Content;
    private Date Ans_Date; //
}
