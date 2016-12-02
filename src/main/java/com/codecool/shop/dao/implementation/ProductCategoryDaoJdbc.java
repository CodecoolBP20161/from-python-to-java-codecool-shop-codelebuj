package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.IdGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductCategoryDaoJdbc extends ConnectionDb implements ProductCategoryDao {

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }

    private static ProductCategoryDaoJdbc instance = null;

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        int id = IdGenerator.getInstance().getNextId();
        category.setId(id);

        String query = "INSERT INTO productcategory (productcategory_id, productcategory_name, productcategory_description, productcategory_department) VALUES (?,?,?,?)";

        try (Connection connection = getConnection(); PreparedStatement safeInput = connection.prepareStatement(query)){
            safeInput.setInt(1, category.getId());
            safeInput.setString(2,category.getName());
            safeInput.setString(3, category.getDescription());
            safeInput.setString(4,category.getDepartment());
            safeInput.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM productcategory WHERE productcategory_id = "+ id+ ";";
        ProductCategory found = null;
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                ProductDaoJdbcImpl productDaoJdbc = new ProductDaoJdbcImpl();
                found = new ProductCategory(resultSet.getInt("productcategory_id"),
                        resultSet.getString("productcategory_name"),
                        resultSet.getString("productcategory_department"),
                        resultSet.getString("productcategory_description"));
                found.setProducts(productDaoJdbc.getBy(found));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public void remove(int id) {
        String query = String.format("DELETE FROM productcategory WHERE productcategory_id = %d;", id);
        executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM productcategory;";

        List<ProductCategory> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                ProductCategory actTodo = new ProductCategory(resultSet.getInt("productcategory_id"),
                        resultSet.getString("productcategory_name"),
                        resultSet.getString("productcategory_department"),
                        resultSet.getString("productcategory_description"));
                resultList.add(actTodo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
