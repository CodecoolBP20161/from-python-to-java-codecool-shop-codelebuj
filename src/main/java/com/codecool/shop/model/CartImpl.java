package com.codecool.shop.model;

import javax.sound.sampled.Line;
import java.io.IOException;
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
        LineItem nullItem = null;
        for (LineItem item : lineItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.decreaseQuantity(1);
                if (item.getQuantity() == 0) {
                    nullItem = item;
                }
            }
        }
        if (nullItem != null) {
            this.remove(nullItem);
        }
    }

    public void deleteAllProduct(Product product){
        LineItem deleteItem = null;
        for (LineItem item : lineItems){
            if (item.getProduct().getId() == product.getId()){
                deleteItem = item;
            }
        }
        if (deleteItem != null) {
            this.remove(deleteItem);
        }


    }

    public void remove(LineItem items) {
        this.lineItems.remove(items);
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