package com.codecool.shop.model;

import org.omg.CORBA.Object;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kakacsu on 2016.11.16..
 */
public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity){
        Map<Product,Integer> cartItem = new HashMap<>();
    }
}