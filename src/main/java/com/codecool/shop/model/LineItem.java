package com.codecool.shop.model;


public class LineItem {
    private Product product;
    private int quantity;
    private float fullPrice;

    public void LineItem(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getFullPrice() {
        fullPrice = this.quantity * this.product.getDefaultPrice();
        return fullPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (this.quantity >= 1) {
            this.quantity -= quantity;

        }
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        ;


    }
}