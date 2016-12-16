package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        try {
            String query = "INSERT INTO line_item (order_id, product_id, quantity, default_price) VALUES (?,?,?,?);";
            PreparedStatement safeInput = getConnection().prepareStatement(query);
            safeInput.setInt(1,order.getId());
            safeInput.setInt(2,lineItem.getProduct().getId());
            safeInput.setInt(3,lineItem.getQuantity());
            safeInput.setFloat(4,lineItem.getProduct().getDefaultPrice());
            safeInput.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


