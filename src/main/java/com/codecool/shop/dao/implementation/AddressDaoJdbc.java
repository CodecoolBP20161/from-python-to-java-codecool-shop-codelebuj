package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.model.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by levente on 2016.12.14..
 */
public class AddressDaoJdbc extends ConnectionDb implements AddressDao {

    private static AddressDaoJdbc instance = null;

    public static AddressDaoJdbc getInstance() {
        if (instance == null) {
            instance = new AddressDaoJdbc();
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
    public void add(Address address) {
        String query1 = "INSERT INTO address (address_id, country, city, zipcode, address_info)" +
                "VALUES ((SELECT COUNT(address_id)+1 from address), '" + address.getCountry() +"','"+address.getCity()+"','"+
                address.getZipCode() + "','" + address.getAddressInfo()+ "');";

        executeQuery(query1);
        address.setId(findId());

    }
    public int findId() {
        String query2 = "SELECT address_id from address ORDER BY address_id DESC LIMIT 1;";
        int id = 0;
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query2);


                while (resultSet.next()){
                    id = resultSet.getInt("address_id");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
