package com.example.joinair.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity   //해당 멤버변수 데이터베이스 테이블 추가 필요함.
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_Item_SEQ")
    @SequenceGenerator(name = "CART_Item_SEQ", sequenceName = "CART_Item_SEQ", allocationSize = 1) // 카트 상품 코드 데이터베이스 추가 필요
    private Integer Cart_Item_Code;   //카트 상품 코드

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Cart_Code")
    private Cart Cart_Code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Pro_Code")
    private Product Pro_Code;


    private Integer Cart_Item_Qua; // 상품 개수


    public static CartItem createCartItem(Cart cart, Product product, Integer amount) {
        CartItem cartItem = new CartItem();
        cartItem.setCart_Code(cart);
        cartItem.setPro_Code(product);
        cartItem.setCart_Item_Qua(amount);
        return cartItem;
    }



    public void addCount(int count) {
        this.Cart_Item_Qua += count;
    }
    public Integer getCart_Item_Code() {
        return Cart_Item_Code;
    }

    public void setCart_Item_Code(Integer cart_Item_Code) {
        Cart_Item_Code = cart_Item_Code;
    }

    public Cart getCart_Code() {
        return Cart_Code;
    }

    public void setCart_Code(Cart cart_Code) {
        Cart_Code = cart_Code;
    }

    public Product getPro_Code() {
        return Pro_Code;
    }

    public void setPro_Code(Product pro_Code) {
        Pro_Code = pro_Code;
    }

    public Integer getCart_Item_Qua() {
        return Cart_Item_Qua;
    }

    public void setCart_Item_Qua(Integer cart_Item_Qua) {
        Cart_Item_Qua = cart_Item_Qua;
    }


}
