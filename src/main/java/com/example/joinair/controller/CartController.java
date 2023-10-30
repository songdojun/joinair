package com.example.joinair.controller;

import com.example.joinair.entity.Item;
import com.example.joinair.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.put("total", total(session));
        return "cart/index";
    }

    @RequestMapping(value = "buy/{Pro_Code}", method = RequestMethod.GET)
    public String buy(@PathVariable("Pro_Code") Integer Pro_Code,
                      HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productService.findOne(Pro_Code), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExists(Pro_Code, cart);
            if(index == -1){
                cart.add(new Item(productService.findOne(Pro_Code), 1));
            }else{
                int quantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:../../cart";
    }

    @RequestMapping(value = "remove/{Pro_Code}", method = RequestMethod.GET)
    public String remove(@PathVariable("Pro_Code") Integer Pro_Code,
                      HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExists(Pro_Code, cart);
        cart.remove(index);
        session.setAttribute("cart",cart);
        return "redirect:../../cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
            HttpServletRequest request,
            HttpSession session) {
            String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for(int i=0; i<cart.size(); i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart",cart);
        return "redirect:../../cart";
    }



    private int isExists(Integer Pro_Code, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getPro_Code() == Pro_Code) {
                return i;
            }
        }
        return -1;
    }

    private double total(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s =0;
        for(Item item : cart){
            s += item.getQuantity()
                    * item.getProduct().getPro_Price().doubleValue();
        }
        return s;
    }

}

