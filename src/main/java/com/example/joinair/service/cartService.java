package com.example.joinair.service;

import com.example.joinair.dto.CART;
import com.example.joinair.mapper.cartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cartService {
    @Autowired
    private cartMapper cartMapper;

    public List<CART> CartList() {
        return cartMapper.CartList();
    }

    public boolean addCart(CART cart) {
        return cartMapper.addCart(cart);
    }

    public boolean removeCart(int cartCode) {
        return cartMapper.removeCart(cartCode);
    }

    public boolean updateCart(CART cart) {
        return cartMapper.updateCart(cart);
    }

    public boolean deleteCart(CART cart) {
        return cartMapper.deleteCart(cart);
    }

}
