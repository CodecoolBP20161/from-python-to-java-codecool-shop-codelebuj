package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import java.sql.Connection;
import java.sql.SQLException;


public class LineItemDaoJdbcImpl extends ConnectionDb implements LineItemDao {

    private static LineItemDaoJdbcImpl instance = null;

    public static LineItemDaoJdbcImpl getInstance() {
        if (instance == null) {
            instance = new LineItemDaoJdbcImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }

    @Override
    public void add(LineItem lineItem, Order order) {
        String query1 = "INSERT INTO line_item (order_id, product_id, quantity, default_price)" +
                "VALUES ((SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1), " + lineItem.getProduct().getId() + "," +
                lineItem.getQuantity() + "," + lineItem.getProduct().getDefaultPrice()+");";
        executeQuery(query1);

    }
}


