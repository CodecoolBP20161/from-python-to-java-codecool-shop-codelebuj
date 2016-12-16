package com.codecool.shop.model;


import com.codecool.shop.model.Product;

import java.util.List;

public interface Cart {
    void addProduct(Product product);
    int getTotalQuantity();
    void removeProduct(Product product);
    void deleteAllProduct(Product product);
    List<LineItem> getLineItems();
}
