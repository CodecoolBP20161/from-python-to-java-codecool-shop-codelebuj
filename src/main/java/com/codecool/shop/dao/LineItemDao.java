package com.codecool.shop.dao;


import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

public interface LineItemDao {
    void add(LineItem lineItem, Order order);


}
