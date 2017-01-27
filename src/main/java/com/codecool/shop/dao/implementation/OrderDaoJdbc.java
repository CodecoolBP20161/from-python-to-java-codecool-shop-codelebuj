package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String query1 = "INSERT INTO orders (order_id, first_name, last_name, email, phone_number, billing_address, shipping_address)" + "VALUES ((SELECT COUNT(order_id)+1 FROM orders),'" +
                order.getFirstName() + "','" +order.getLastName() + "','" +order.getEmail() + "','"+ order.getPhoneNumber() + "'," +
                order.getBillingAddress().getId() + "," + order.getShippingAddress().getId() +");";
        executeQuery(query1);

    }

    public List<Order> getAll(){
        String query = "select * from orders inner join address on orders.shipping_address = address.address_id and orders.billing_address = address.address_id;";
        List<Order> resultList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Address billingAddress = new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("zipCode"),
                        resultSet.getString("addressInfo"));
                Address shippingAddress = new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("zipCode"),
                        resultSet.getString("addressInfo"));
                Order actTodo = new Order(resultSet.getInt("order_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        billingAddress, shippingAddress);
                resultList.add(actTodo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;

    }

    public Order find(int id) {
        String query = "select * from orders inner join address on orders.billing_address = address.address_id and orders.shipping_address = address.address_id WHERE order_id = "+ id+ ";";
        Order found = null;
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                Address billingAddress = new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("zipCode"),
                        resultSet.getString("addressInfo"));
                Address shippingAddress = new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("zipCode"),
                        resultSet.getString("addressInfo"));

                found = new Order(
                        resultSet.getInt("order_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        billingAddress, shippingAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
}
