package com.codecool.shop.model;


import com.codecool.shop.model.Product;

public interface Cart {
    void addProduct(Product product);
    int getTotalQuantity();
}
