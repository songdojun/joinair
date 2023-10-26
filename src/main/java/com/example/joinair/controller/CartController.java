package com.example.joinair.controller;

import com.example.joinair.entity.Cart;
import com.example.joinair.entity.Product;
import com.example.joinair.service.CartService;
import com.example.joinair.service.ProductBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.example.joinair.service.ProductAdService.regist;


@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart/regist")
    public String cartRegistForm(){return "cartregist";}

    @PostMapping("/cart/registpro")
    public String cartRegistPro(Cart cart, Model model, MultipartFile file)throws Exception{
        regist(cart,file);

        model.addAttribute("message", "파일 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/cart/list");
        return "message";
    }

    @GetMapping("/cart/list")
    public String cartList(Model model,
                                @PageableDefault(page = 0, size = 10, sort = "Cart_Code", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(name = "searchOption", required = false) String searchOption,
                                @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        Page<Cart> list = cartService.cartSearchList(searchOption, searchKeyword, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // Add search parameters to the model
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchOption", searchOption);
        model.addAttribute("searchKeyword", searchKeyword); // Pass search keyword to the view

        return "cartlist";
    }


    @GetMapping("/cart/view")
    public String cartView(Model model, Integer Cart_Code){
        model.addAttribute("Cart",cartService.cartView(Cart_Code).orElse(null));
        return "cartview";
    }



    @GetMapping("/cart/delete")
    public String cartDelete(Integer Cart_Code){
        cartService.cartDelete(Cart_Code);

        return "redirect:/cart/list";
    }

    @GetMapping("/cart/modify/{Cart_Code}")
    public String cartModify(@PathVariable("Cart_Code") Integer Cart_Code, Model model){
        Cart cart = cartService.cartView(Cart_Code).orElse(null);

        if(cart==null){
            return "redirect:/error";
        }
        model.addAttribute("cart", cart);
        return "cartmodify";
    }

    @PostMapping("/cart/update/{Cart_Code}")
    public String cartUpdate(@PathVariable("Cart_Code")Integer Cart_Code, Cart updateCart, Model model, MultipartFile file) throws Exception{
        Cart productTemp = productBuyService.productbuyView(Pro_Code).orElse(null);

        if(productTemp !=null){
            productTemp.setPro_Code(updateProduct.getPro_Code());
            productTemp.setCate_No(updateProduct.getCate_No());
            productTemp.setPro_Name(updateProduct.getPro_Name());
            productTemp.setPro_Price(updateProduct.getPro_Price());
            productTemp.setPro_Inventory(updateProduct.getPro_Inventory());
            productTemp.setPro_Weight(updateProduct.getPro_Weight());

            regist(productTemp,file);
        }

        model.addAttribute("message", "상품 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/productbuy/list");
        return  "message";
    }



}
