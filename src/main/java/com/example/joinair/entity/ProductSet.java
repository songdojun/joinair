package com.example.joinair.entity;

public class ProductSet {

    private Product product;
    private Integer quantity;
    public ProductSet(Product product, Integer quantity){
        this.product = product;
        this.quantity=quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "ProductSet{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
