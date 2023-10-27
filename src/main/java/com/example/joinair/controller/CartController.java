package com.example.joinair.controller;

import com.example.joinair.entity.Cart;
import com.example.joinair.entity.CartItem;
import com.example.joinair.service.CartService;
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
    public String cartUpdate(@PathVariable("Cart_Code") Integer Cart_Code, Cart updateCart, Model model, MultipartFile file) throws Exception {
        Cart cartTemp = cartService.cartView(Cart_Code).orElse(null);

        if (cartTemp != null) {
            // 기존의 Cart에 변경 사항을 업데이트합니다.
            cartTemp.setCart_Code(updateCart.getCart_Code()); // 카트 코드
            cartTemp.setUser_Id(updateCart.getUser_Id()); // 유저 아이디
            cartTemp.setCart_Qua(updateCart.getCart_Qua()); // 카트에 담긴 총 금액
            cartTemp.setCartItems(updateCart.getCartItems()); // 카트에 담긴 아이템들(List)

            // 여기서 CartItem 엔티티의 값을 업데이트합니다.
            for (CartItem cartItem : cartTemp.getCartItems()) {
                // 여기에서 각 CartItem의 값을 업데이트합니다.
                // updateCart에서 필요한 값을 가져와서 각 CartItem에 설정합니다.
                 cartItem.setCart_Item_Qua(updateCart.getCart_Item_Qua());
            }

            // Cart 엔티티를 다시 저장합니다.
            cartService.saveCart(cartTemp);
            model.addAttribute("message", "상품 수정이 완료되었습니다.");
            model.addAttribute("searchUrl", "/cart/list");
            return "message";
        } else {
            // 처리 실패나 오류 처리
            return "error";
        }
    }




}
