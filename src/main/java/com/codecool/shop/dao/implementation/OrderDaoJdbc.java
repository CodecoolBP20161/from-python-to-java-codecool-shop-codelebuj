package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoJdbc extends ConnectionDb implements OrderDao {

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }



    private static OrderDaoJdbc instance = null;

    public static OrderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Order order){
        try {
            String query = "INSERT INTO orders (order_id, first_name, last_name, email, phone_number, billing_address, shipping_address) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement safeInput = getConnection().prepareStatement(query);
            safeInput.setInt(1,order.getId());
            safeInput.setString(2,order.getFirstName());
            safeInput.setString(3,order.getLastName());
            safeInput.setString(4,order.getEmail());
            safeInput.setString(5,order.getPhoneNumber());
            safeInput.setInt(6,order.getBillingAddress().getId());
            safeInput.setInt(7,order.getShippingAddress().getId());
            safeInput.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
