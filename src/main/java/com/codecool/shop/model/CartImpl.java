package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class CartImpl implements Cart {
    private List<LineItem> lineItems = new ArrayList<>();
    private int totalQuantity;
    private float totalPrice;


    public CartImpl() {
    }

    @Override
    public void addProduct(Product product) {
        boolean isIn = false;
        for (LineItem item : lineItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity(1);
                isIn = true;
            }
        }
        if (isIn == false) {
            lineItems.add(new LineItem(product, 1));
        }
    }


    @Override
    public void removeProduct(Product product){
        for (LineItem item : lineItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.decreaseQuantity(1);
            }
        }

    }


    @Override
    public int getTotalQuantity() {
        this.totalQuantity = 0;
        for (LineItem item : lineItems) {
            this.totalQuantity += item.getQuantity();
        }
        return this.totalQuantity;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public float totalPrice() {
        float totalPrice = 0;
        for (LineItem item : lineItems) {
            totalPrice += item.getQuantity() * item.getProduct().getDefaultPrice();
        }
        return totalPrice;
    }

}