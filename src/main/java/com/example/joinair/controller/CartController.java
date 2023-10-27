package com.example.joinair.controller;


import com.example.joinair.entity.Cart;
import com.example.joinair.entity.Product;
import com.example.joinair.entity.ProductSet;
import com.example.joinair.entity.Users;
import com.example.joinair.service.CartService;
import com.example.joinair.service.ProductAdService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductAdService productAdService; // ProductAdService를 주입

    @RequestMapping("cartAdd")
    public ModelAndView add(Integer Pro_Code, Integer quantity, HttpSession session) {
        // 장바구니 정보를 session에 등록함
        ModelAndView mav = new ModelAndView("cart/cart");

        // 1. Pro_Code에 해당하는 상품 조회
        Product product = productAdService.productadView(Pro_Code).orElse(null);

        if (product == null) {
            // 상품이 존재하지 않는 경우에 대한 예외 처리 (원하는 방법으로 처리할 수 있음)
            mav.addObject("message", "상품이 존재하지 않습니다.");
            return mav;
        }

        // session에 등록된 Cart객체 조회
        Cart cart = (Cart) session.getAttribute("CART");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("CART", cart);
        }

        // 장바구니 추가 로직을 CartService를 통해 호출
        cartService.addToCart(cart, product, quantity);

        mav.addObject("cart", cart);
        mav.addObject("message", "장바구니에 상품이 추가되었습니다.");
        return mav;
    }
    /*
        index 파라미터 : cart, productSetList 객체의 순서
     */
    @RequestMapping("cartDelete")
    public ModelAndView delete(int index, HttpSession session){
        ModelAndView mav = new ModelAndView("cart/cart");
        Cart cart = (Cart)session.getAttribute("CART");
        //delSet : index에 해당하는 삭제된 객체
        ProductSet delSet = cart.getProductSetList().remove(index);
        mav.addObject("cart",cart);
        mav.addObject("meeage", delSet.getProduct().getPro_Name()+"이(가) 장바구니에서 삭제");
        return mav;
    }

    @RequestMapping("cartView")
    public ModelAndView view(HttpSession session){
        ModelAndView mav = new ModelAndView("cart/cart");
        Cart cart = (Cart)session.getAttribute("CART");
        mav.addObject("cart",cart);
        return mav;
    }
    //로그인이 되어야 실행 가능하도록 AOP부분 추가하기
    //장바구니에 주문상품이 없는 경우 실행 불가 AOP 부분 추가하기
    @RequestMapping("checkout")
    public String checkout(HttpSession session){
        return null;
    }

    /*주문 확정
    1.로그인, 장바구니 검증 필요 =>AOP로 설정
    2.장바구니 상품을 saleitem 테이블에 저장하기
    3. 로그인 정보로 주문정보 (saleitem)테이블에 저장하기
    4. 장바구니 상품 제거
    5. 주문 정보 end.jsp
     */

//    @RequestMapping("end")
//    public ModelAndView checkend(HttpSession session){
//        ModelAndView mav = new ModelAndView();
//        Cart cart = (Cart)session.getAttribute("CART");
//        Users users = (Users)session.getAttribute("users");
//        Sale sale = service.checkend(users,cart);
//        session.removeAttribute("CART");
//        cart.getProductSetList().clear(); // 장바구니 상품 제거
//        mav.addObject("sale",sale);
//        return mav;
//    }
}
