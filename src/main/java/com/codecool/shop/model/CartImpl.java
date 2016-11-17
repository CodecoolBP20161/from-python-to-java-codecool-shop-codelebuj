package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class CartImpl implements Cart {
    private List<LineItem> lineItems;
    private int totalQuantity;
    private int totalPrice;


    public CartImpl() {
        lineItems = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        if (lineItems.size() == 0) {
            lineItems.add(new LineItem(product, 1));
        }
        else {
            boolean isIn = false;
            for (LineItem item : lineItems) {
                if (item.getProduct() == product) {
                    item.increaseQuantity(1);
                    isIn = true;
                    break;
                }
            }
            if (isIn == false) {
                lineItems.add(new LineItem(product,1));
            }
        }
    }

    @Override
    public int getTotalQuantity() {
        this.totalQuantity = 0;
        for (LineItem item : this.lineItems){
            this.totalQuantity += item.getQuantity();
        }
        return  this.totalQuantity;
    }
}