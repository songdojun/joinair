package com.example.joinair.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ")
    @SequenceGenerator(name = "REVIEW_SEQ", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private Integer Rev_No;


    private String User_Id;         // UserID
    private Integer Pro_Code;           // 상품코드
    private Integer Pay_No;             // 결제번호
    private String Rev_Title;       // 리뷰제목
    private String Rev_Content;     // 리뷰내용
    private String Rev_Writer;      // 리뷰작성자
    private Date Rev_Date;

    public String getRev_Title() {
        return Rev_Title;
    }

    public void setRev_Title(String rev_Title) {
        Rev_Title = rev_Title;
    }

    public String getRev_Content() {
        return Rev_Content;
    }

    public void setRev_Content(String rev_Content) {
        Rev_Content = rev_Content;
    }


}
