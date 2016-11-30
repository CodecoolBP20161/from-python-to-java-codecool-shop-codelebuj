package com.codecool.shop.dao.implementation;

import java.sql.*;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao{

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static ProductDaoJdbcImpl instance = null;


    public static ProductDaoJdbcImpl getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbcImpl();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (product_name, product_description, product_defaultPrice, product_defaultCurrency, product_supplier, product_productCategory) " +
                "VALUES ('" + product.getName() + "','" + product.getDescription() +"', "+product.getDefaultPrice()+",'" + product.getDefaultCurrency()+"'," +product.getProductCategory().getId()+","+product.getProductCategory().getId()+");";
        executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product WHERE product_id = "+ id+ ";";
        Product found = null;
        ProductCategoryDaoJdbc cat = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc sup = new SupplierDaoJdbc();
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                found = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("product_defaultprice"),
                        resultSet.getString("product_defaultcurrency"),
                        resultSet.getString("product_description"),
                        cat.find(resultSet.getInt("product_productcategory")),
                        sup.find(resultSet.getInt("product_supplier")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public void remove(int id) {

        String query = "DELETE FROM product WHERE product_id = '" + id +"';";
        executeQuery(query);

    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";
        List<Product> resultList = new ArrayList<>();

        ProductCategoryDaoJdbc cat = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc sup = new SupplierDaoJdbc();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Product actTodo = new Product(
                                resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getFloat("product_defaultprice"),
                                resultSet.getString("product_description"),
                                resultSet.getString("product_defaultcurrency"),
                                cat.find(resultSet.getInt("product_productcategory")),
                                sup.find(resultSet.getInt("product_supplier")));
                resultList.add(actTodo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        ProductCategoryDaoJdbc cat = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc sup = new SupplierDaoJdbc();

        String query = String.format("SELECT * FROM product where product_supplier = %d", supplier.getId());
        List<Product> productsBySupp = new ArrayList<Product>();
        Product target;

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                target = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("product_defaultprice"),
                        resultSet.getString("product_description"),
                        resultSet.getString("product_defaultcurrency"),
                        cat.find(resultSet.getInt("product_productcategory")),
                        sup.find(resultSet.getInt("product_supplier")));
                productsBySupp.add(target);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsBySupp;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        ProductCategoryDaoJdbc cat = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc sup = new SupplierDaoJdbc();

        String query = String.format("SELECT * FROM product where product_productcategory = %d", productCategory.getId());
        List<Product> productsByCat = new ArrayList<Product>();
        Product target;

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                target = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("product_defaultprice"),
                        resultSet.getString("product_description"),
                        resultSet.getString("product_defaultcurrency"),
                        cat.find(resultSet.getInt("product_productcategory")),
                        sup.find(resultSet.getInt("product_supplier")));
                productsByCat.add(target);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsByCat;
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
