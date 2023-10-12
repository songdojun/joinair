package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ANSWER {
    private int ans_No;
    private int qna_No;
    private String ans_Content;
    private Date ans_Date; //
}
