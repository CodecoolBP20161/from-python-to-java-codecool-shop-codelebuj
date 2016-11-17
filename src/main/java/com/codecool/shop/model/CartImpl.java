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
                    item.increaseQuantity(1);
                    isIn = true;
                    break;
                }
                else{
                    System.out.println("lineitem does not exist");
                }
            }
            if (isIn == false) {
                System.out.println("If out of for"+product);
                lineItems.add(new LineItem(product,1));
            }
        }
    }

    @Override
    public int getTotalQuantity() {
        this.totalQuantity = 0;
        for (LineItem item : this.lineItems){
            System.out.println(item.getProduct() + "/" + item.getQuantity());
            this.totalQuantity += item.getQuantity();
        }
//        System.out.println("getTotatQuantity method"+this.totalQuantity);
        return  this.totalQuantity;
    }
}