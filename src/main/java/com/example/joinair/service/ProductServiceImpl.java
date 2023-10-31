package com.example.joinair.service;

import com.example.joinair.dao.ProductDAO;
import com.example.joinair.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Iterable<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findOne(int Pro_Code) {
        return productDAO.findById(Pro_Code).orElse(null);
    }
}
