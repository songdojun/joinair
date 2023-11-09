package com.example.joinair.controller;

import com.example.joinair.entity.Item;
import com.example.joinair.entity.Product;
import com.example.joinair.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);


    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.put("total", total(session));
        return "cart/index";
    }

    @RequestMapping(value = "buy/{Pro_Code}", method = RequestMethod.GET)
    public String buy(@PathVariable("Pro_Code") Integer Pro_Code,
                      @RequestParam(name = "quantity", defaultValue = "1") Integer quantity,
                      HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productService.findOne(Pro_Code), quantity));
            session.setAttribute("cart", cart);
            // 로그 추가
            logCartDetails("New cart created", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            boolean found = false;
            for (Item item : cart) {
                if (item.getProduct().getPro_Code().equals(Pro_Code)) {
                    int newQuantity = item.getQuantity() + quantity;
                    item.setQuantity(newQuantity);

                    // 무게 업데이트
                    Product product = item.getProduct();
                    // 상품 무게 = 상품 1개의 무게 * 수량
                    product.setPro_Weight(productService.findOne(Pro_Code).getPro_Weight() * newQuantity);
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new Item(productService.findOne(Pro_Code), quantity));
            }
            session.setAttribute("cart", cart);
            // 로그 추가
            logCartDetails("Item added to cart 동일상품 카트 추가", cart);
        }

        // 장바구니에 상품을 추가한 후, total 다시 계산
        double total = total(session);
        session.setAttribute("total", total);

        // 로그 추가
        logger.info("Total updated in 'buy' method - Total: {}", total);

        return "redirect:../../cart";
    }

    private void logCartDetails(String message, List<Item> cart) {
        logger.info("{} - Cart Details:", message);
        for (Item item : cart) {
            logger.info("Product: {}, Quantity: {}, Weight: {}",
                    item.getProduct().getPro_Name(), item.getQuantity(), item.getProduct().getPro_Weight());
        }
    }





    @RequestMapping(value = "remove/{Pro_Code}", method = RequestMethod.GET)
    public ResponseEntity<String> remove(@PathVariable("Pro_Code") Integer Pro_Code, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double total = (Double) session.getAttribute("total");

        if (cart != null) {
            int index = -1;
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getProduct().getPro_Code().equals(Pro_Code)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                Item removedItem = cart.get(index);
                double subtotal = removedItem.getProduct().getPro_Price().doubleValue() * removedItem.getQuantity();
                total -= subtotal;
                cart.remove(index);

                // 제거된 항목의 정보만 세션에서 제거
                session.setAttribute("total", total);

                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }





    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");

        // quantities가 null이 아닌 경우에 대한 로직 수행
        if (quantities != null) {
            // 세션에서 현재 장바구니 정보를 가져옵니다.
            List<Item> cart = (List<Item>) session.getAttribute("cart");

            // cart가 null이 아닌 경우에 대한 로직 수행
            if (cart != null) {
                // 각 상품의 수량을 업데이트
                for (int i = 0; i < cart.size(); i++) {
                    int updatedQuantity = Integer.parseInt(quantities[i]);
                    cart.get(i).setQuantity(updatedQuantity);
                }

                // 총 가격을 다시 계산
                double total = total(session);

                // 로그 추가
//                logger.info("Cart updated in 'update' method - Cart: {}, Total: {}", cart, total);

                // 세션에 갱신된 장바구니와 총 가격을 저장
                session.setAttribute("cart", cart);
                session.setAttribute("total", total);
            }
        }

        // 널포인트익셉션 방지를 위해 리다이렉트 대신 직접 리턴
        return "cart/index";
    }



    private int isExists(Integer Pro_Code, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getPro_Code() == Pro_Code) {
                return i;
            }
        }
        return -1;
    }

    private double total(HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s = 0;
        for (Item item : cart) {
            s += item.getQuantity()
                    * item.getProduct().getPro_Price().doubleValue();
        }
        return s;
    }


}

