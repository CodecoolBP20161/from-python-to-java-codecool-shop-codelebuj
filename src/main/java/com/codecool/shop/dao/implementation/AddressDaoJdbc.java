package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        int id = IdGenerator.getInstance().getNextId();
        address.setId(id);

        try {
            String query = "INSERT INTO address (address_id, country, city, zipcode, address_info) VALUES (?,?,?,?,?);";
            PreparedStatement safeInput = getConnection().prepareStatement(query);
            safeInput.setInt(1,address.getId());
            safeInput.setString(2,address.getCountry());
            safeInput.setString(3,address.getCity());
            safeInput.setString(4, address.getZipCode());
            safeInput.setString(5, address.getAddressInfo());
            safeInput.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
