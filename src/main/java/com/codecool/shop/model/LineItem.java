package com.codecool.shop.model;

import org.omg.CORBA.Object;



public class LineItem {
    private Product product;
    private int quantity;

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

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;


    }
}