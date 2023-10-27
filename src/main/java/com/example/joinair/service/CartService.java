package com.example.joinair.service;
import com.example.joinair.entity.Cart;
import com.example.joinair.entity.Product;
import com.example.joinair.entity.ProductSet;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    public void addToCart(Cart cart, Product product, int quantity) {
        ProductSet productSet = new ProductSet(product, quantity);

        // Check if the product is already in the cart
        for (ProductSet ps : cart.getProductSetList()) {
            if (ps.getProduct().getPro_Code().equals(productSet.getProduct().getPro_Code())) {
                ps.setQuantity(ps.getQuantity() + productSet.getQuantity());
                return;
            }
        }

        // If the product is not in the cart, add it as a new entry
        cart.getProductSetList().add(productSet);
    }
}
