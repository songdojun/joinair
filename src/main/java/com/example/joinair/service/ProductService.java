package com.example.joinair.service;

import com.example.joinair.entity.Product;

public interface ProductService {

    Iterable<Product> findAll();
    Product findOne(int Pro_Code);

}
