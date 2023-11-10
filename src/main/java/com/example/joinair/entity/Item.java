package com.example.joinair.entity;

public class Item {
    private Product product;   //Product객체
    private int quantity; //상품 수량

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item() {
        super();
    }

    public int getSubtotal() {
        if (product != null) {
            return product.getPro_Price() * quantity;
        } else {
            return (int) 0.0;
        }
    }

}
