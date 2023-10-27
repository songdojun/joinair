package com.example.joinair.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ")
    @SequenceGenerator(name = "CART_SEQ", sequenceName = "CART_SEQ", allocationSize = 1)   //자동증가 시퀀스 생성필요 => CART테이블
    private Integer Cart_Code;   // 장바구니 번호


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Users User_Id;   // UserID    구매자 ID

    private Integer Cart_Qua; //     Cart에 담긴 총 상품 개수



    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    public Integer getCart_Item_Qua() {
        int totalQua = 0;
        for (CartItem cartItem : cartItems) {
            totalQua += cartItem.getCart_Item_Qua();
        }
        return totalQua;
    }

    public static Cart createCart(Users User_Id) {
        Cart cart = new Cart();
        cart.setCart_Qua(0);
        cart.setUser_Id(User_Id);
        return cart;
    }


    public Integer getCart_Code() {
        return Cart_Code;
    }

    public void setCart_Code(Integer cart_Code) {
        Cart_Code = cart_Code;
    }

    public Users getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(Users user_Id) {
        User_Id = user_Id;
    }

    public Integer getCart_Qua() {
        return Cart_Qua;
    }

    public void setCart_Qua(Integer cart_Qua) {
        Cart_Qua = cart_Qua;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
