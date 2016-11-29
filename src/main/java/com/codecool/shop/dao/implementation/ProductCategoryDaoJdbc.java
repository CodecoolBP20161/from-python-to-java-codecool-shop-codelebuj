package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by levente on 2016.11.29..
 */
public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static ProductCategoryDaoJdbc instance = null;


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        try {
            String query = "INSERT INTO productcategory (productcategory_name, productcategory_description, productcategory_department) VALUES (?,?,?)";
            PreparedStatement safeInput = getConnection().prepareStatement(query);
            safeInput.setString(1,category.getName());
            safeInput.setString(2, category.getDescription());
            safeInput.setString(3,category.getDepartment());
            safeInput.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

}
