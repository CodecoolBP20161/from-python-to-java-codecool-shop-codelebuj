package com.codecool.shop.model;
import java.util.ConcurrentModificationException;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;

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
            System.out.println("The lineItems is empty"+product);
        }
        else {
            boolean isIn = false;
            for (LineItem item : lineItems) {
                System.out.println("The for is started");
                if (item.getProduct() == product) {
                    System.out.println("the lineItem conatains that product"+product);
                    item.setQuantity(item.getQuantity());
                }
                else{
                    System.out.println("bollean true");
                    isIn = true;
                }
            }
            if(isIn == true) {
                System.out.println("If out of for"+product);
                lineItems.add(new LineItem(product,0));
            }
        }
    }

    @Override
    public int getTotalQuantity() {
        for (LineItem quantity : this.lineItems){
            this.totalQuantity += quantity.getQuantity();
        }
        System.out.println(this.totalQuantity);
        return  this.totalQuantity;
    }
}
