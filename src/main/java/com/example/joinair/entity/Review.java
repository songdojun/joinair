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

    private String User_Id;
    private Integer Pro_Code;
    private Integer Pay_No;
    private String Rev_Title;
    private String Rev_Content;
    private String Rev_Writer;
    private Date Rev_Date;
    private String Rev_Filepath;
    private String Rev_Filename;

    public String getRev_Filename() {
        return Rev_Filename;
    }

    public void setRev_Filename(String rev_Filename) {
        Rev_Filename = rev_Filename;
    }

    public String getRev_Filepath() {
        return Rev_Filepath;
    }

    public void setRev_Filepath(String rev_Filepath) {
        Rev_Filepath = rev_Filepath;
    }

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

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public Integer getPro_Code() {
        return Pro_Code;
    }

    public void setPro_Code(Integer pro_Code) {
        Pro_Code = pro_Code;
    }

    public Integer getPay_No() {
        return Pay_No;
    }

    public void setPay_No(Integer pay_No) {
        Pay_No = pay_No;
    }

    public String getRev_Writer() {
        return Rev_Writer;
    }

    public void setRev_Writer(String rev_Writer) {
        Rev_Writer = rev_Writer;
    }

    public Date getRev_Date() {
        return Rev_Date;
    }

    public void setRev_Date(Date rev_Date) {
        Rev_Date = rev_Date;
    }

    public Integer getRev_No() {
        return Rev_No;
    }

    public void setRev_No(Integer rev_No) {
        Rev_No = rev_No;
    }
}
