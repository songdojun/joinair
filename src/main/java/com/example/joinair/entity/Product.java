package com.example.joinair.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Product {

    public Integer getPro_Code() {
        return Pro_Code;
    }

    public void setPro_Code(Integer pro_Code) {
        Pro_Code = pro_Code;
    }

    public Integer getCate_No() {
        return Cate_No;
    }

    public void setCate_No(Integer cate_No) {
        Cate_No = cate_No;
    }

    public String getPro_Name() {
        return Pro_Name;
    }

    public void setPro_Name(String pro_Name) {
        Pro_Name = pro_Name;
    }

    public Integer getPro_Price() {
        return Pro_Price;
    }

    public void setPro_Price(Integer pro_Price) {
        Pro_Price = pro_Price;
    }

    public Integer getPro_Inventory() {
        return Pro_Inventory;
    }

    public void setPro_Inventory(Integer pro_Inventory) {
        Pro_Inventory = pro_Inventory;
    }

    public Integer getPro_Weight() {
        return Pro_Weight;
    }

    public void setPro_Weight(Integer pro_Weight) {
        Pro_Weight = pro_Weight;
    }

    public Date getPro_Reg_Date() {
        return Pro_Reg_Date;
    }

    public void setPro_Reg_Date(Date pro_Reg_Date) {
        Pro_Reg_Date = pro_Reg_Date;
    }

    public String getPro_Filepath() {
        return Pro_Filepath;
    }

    public void setPro_Filepath(String pro_Filepath) {
        Pro_Filepath = pro_Filepath;
    }

    public String getPro_Filename() {
        return Pro_Filename;
    }

    public void setPro_Filename(String pro_Filename) {
        Pro_Filename = pro_Filename;
    }

    public String getPro_Des() {
        return Pro_Des;
    }

    public void setPro_Des(String pro_Des) {
        Pro_Des = pro_Des;
    }

    @Id
    private Integer Pro_Code;
    private Integer Cate_No;
    private String Pro_Name;
    private Integer Pro_Price;
    private Integer Pro_Inventory;
    private Integer Pro_Weight;
    private Date Pro_Reg_Date;
    private String Pro_Filepath;
    private String Pro_Filename;


    private  String Pro_Des;

    @ManyToOne
    @JoinColumn(name = "Cate_No", referencedColumnName = "Cate_No", insertable = false, updatable = false)
    private Category category;

    @PrePersist
    protected  void onCreate(){
        Pro_Reg_Date = new Date();
    }




    // 다른 필드들

    @OneToOne
    @JoinColumn(name = "Pro_Code", referencedColumnName = "Pro_Code", insertable = false, updatable = false)
    private Review review;


    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    // Review 엔티티의 필드에 접근하기 위한 Getter 메서드를 추가합니다.

    public Integer getRev_No() {
        return review != null ? review.getRev_No() : null;
    }

    public String getRev_Title() {
        return review != null ? review.getRev_Title() : null;
    }

    public String getRev_Filepath() {
        return review != null ? review.getRev_Filepath() : null;
    }

    public String getRev_Writer() {
        return review != null ? review.getRev_Writer() : null;
    }

    public String getRev_Content() {
        return review != null ? review.getRev_Content() : null;
    }

    public Date getRev_Date() {
        return review != null ? review.getRev_Date() : null;
    }





}
