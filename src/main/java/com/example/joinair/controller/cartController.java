package com.example.joinair.controller;

import com.example.joinair.dto.CART;
import com.example.joinair.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class cartController {
    @Autowired
    private cartService cartService;

    @GetMapping("/carttest")
    public ModelAndView cartList() {
        List<CART> cartList = cartService.CartList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("carttest");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("carttest", cartList);
        return mv;
    }


    @PostMapping("/addCart")
    public boolean addCart(@RequestBody CART cart) {
        return cartService.addCart(cart);
    }

    @DeleteMapping("/removeCart/{cartCode}")
    public boolean removeCart(@PathVariable int cartCode) {
        return cartService.removeCart(cartCode);
    }

    @PutMapping("/updateCart")
    public boolean updateCart(@RequestBody CART cart) {
        return cartService.updateCart(cart);
    }

    @PutMapping("/deleteCart")
    public boolean deleteCart(@RequestBody CART cart) {
        return cartService.deleteCart(cart);
    }
}