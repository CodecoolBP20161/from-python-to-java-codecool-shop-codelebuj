package com.codecool.shop.model;


import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class CartImpl implements Cart {
    private List<LineItem> lineItems;
    private int totalQuantity;
    private int totalPrice;0


    public CartImpl() {
        lineItems = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        if ( this.alreadyInCart(product) ) {
            // increment quantity
        } else {
            lineItems.add( new LineItem(product, 1) );
        }

    }

    @Override
    public int getTotalQuantity() {
        return 0;
    }
}
