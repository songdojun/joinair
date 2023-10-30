package com.example.joinair.dao;

import com.example.joinair.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductDAO extends
        CrudRepository<Product , Integer> {
}
