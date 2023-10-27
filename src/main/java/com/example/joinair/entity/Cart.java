package com.example.joinair.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ProductSet> productSetList = new ArrayList<>();

    public List<ProductSet> getProductSetList(){
        return productSetList;
    }

    public void push(ProductSet productSet){
        for(ProductSet ps : productSetList){
            if(productSet.getProduct().getPro_Code() == ps.getProduct().getPro_Code()){
                ps.setQuantity(ps.getQuantity() + productSet.getQuantity());
                return;
            }
        }
        productSetList.add(productSet);
    }
}
