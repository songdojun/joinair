package com.example.joinair.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CATEGORY")
public class Category {

    public Integer getCate_No() {
        return Cate_No;
    }

    public void setCate_No(Integer cate_No) {
        Cate_No = cate_No;
    }

    public String getCate_Name() {
        return Cate_Name;
    }

    public void setCate_Name(String cate_Name) {
        Cate_Name = cate_Name;
    }

    @Id
    private Integer Cate_No; // 카테고리식별번호

    private String Cate_Name; // 카테고리이름

    // 생성자, getter 및 setter 등을 추가해야 합니다.
}
