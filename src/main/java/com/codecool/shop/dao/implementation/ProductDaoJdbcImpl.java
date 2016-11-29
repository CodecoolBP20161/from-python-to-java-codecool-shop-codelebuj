package com.codecool.shop.dao.implementation;

import java.sql.*;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao{

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "alma";


    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (product_name, product_description, product_defaultPrice, product_defaultCurrency, product_supplier, product_productCategory) " +
                "VALUES ('" + product.getName() + "','" + product.getDescription() +"', "+product.getDefaultPrice()+",'" + product.getDefaultCurrency()+"'," +product.getProductCategory().getId()+","+product.getProductCategory().getId()+");";
        System.out.println(query);
        executeQuery(query);
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
