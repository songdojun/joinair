package com.example.joinair.mapper;

import com.example.joinair.dto.CART;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface cartMapper {
    List<CART> CartList();

    boolean addCart(CART cart);

    boolean removeCart(int cartCode);

    boolean updateCart(CART cart);

    boolean deleteCart(CART cart);
}
