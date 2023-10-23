package com.example.joinair.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Notice {

    @Id   //id 기본키 설정 Not_no 1 씩 증가한다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ")
    @SequenceGenerator(name = "NOTICE_SEQ", sequenceName = "NOTICE_SEQ", allocationSize = 1)//1씩 증가
    private  Integer Not_No;
    private  String Not_Title;
    private String Not_Content;
    private Date Not_Date;
    private String Not_Writer;
    private String Not_Filepath;
    private String Not_Filename;


    @PrePersist
    protected void onCreate() {
        Not_Date = new Date();
    }
    public String getNot_Filepath() {
        return Not_Filepath;
    }

    public Integer getNot_No() {
        return Not_No;
    }

    public void setNot_No(Integer not_No) {
        Not_No = not_No;
    }

    public String getNot_Title() {
        return Not_Title;
    }

    public void setNot_Title(String not_Title) {
        Not_Title = not_Title;
    }

    public String getNot_Content() {
        return Not_Content;
    }

    public void setNot_Content(String not_Content) {
        Not_Content = not_Content;
    }

    public Date getNot_Date() {
        return Not_Date;
    }

    public void setNot_Date(Date not_Date) {
        Not_Date = not_Date;
    }

    public String getNot_Writer() {
        return Not_Writer;
    }

    public void setNot_Writer(String not_Writer) {
        Not_Writer = not_Writer;
    }

    public void setNot_Filepath(String not_Filepath) {
        Not_Filepath = not_Filepath;
    }

    public String getNot_Filename() {
        return Not_Filename;
    }

    public void setNot_Filename(String not_Filename) {
        Not_Filename = not_Filename;
    }


}
