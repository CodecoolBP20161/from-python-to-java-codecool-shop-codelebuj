package com.codecool.shop.dao.implementation;

import java.sql.*;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl extends ConnectionDb implements ProductDao {

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }

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
        String query = "select * from product inner join productcategory on product.product_productcategory = productcategory.productcategory_id inner join supplier on product.product_supplier = supplier.supplier_id WHERE product_id = "+ id+ ";";
        Product found = null;
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                ProductCategory productCategory= new ProductCategory(
                        resultSet.getInt("productcategory_id"),
                        resultSet.getString("productcategory_name"),
                        resultSet.getString("productcategory_department"),
                        resultSet.getString("productcategory_description"));
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));

                found = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("product_defaultprice"),
                        resultSet.getString("product_defaultcurrency"),
                        resultSet.getString("product_description"),
                        productCategory, supplier);
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
        String query = "select * from product inner join productcategory on product.product_productcategory = productcategory.productcategory_id inner join supplier on product.product_supplier = supplier.supplier_id;";
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                ProductCategory productCategory= new ProductCategory(
                        resultSet.getInt("productcategory_id"),
                        resultSet.getString("productcategory_name"),
                        resultSet.getString("productcategory_department"),
                        resultSet.getString("productcategory_description"));
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));
                Product actTodo = new Product(
                                resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getFloat("product_defaultprice"),
                                resultSet.getString("product_defaultcurrency"),
                                resultSet.getString("product_description"),
                                productCategory, supplier);
                resultList.add(actTodo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = String.format("select * from product inner join productcategory on product.product_productcategory = productcategory.productcategory_id inner join supplier on product.product_supplier = supplier.supplier_id where product_supplier = %d;", supplier.getId());
        List<Product> productsBySupp = new ArrayList<Product>();

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                ProductCategory productCategory= new ProductCategory(
                        resultSet.getInt("productcategory_id"),
                        resultSet.getString("productcategory_name"),
                        resultSet.getString("productcategory_department"),
                        resultSet.getString("productcategory_description"));
                Supplier supp = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));
                Product target = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("product_defaultprice"),
                        resultSet.getString("product_defaultcurrency"),
                        resultSet.getString("product_description"),
                        productCategory, supp);
                productsBySupp.add(target);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsBySupp;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = String.format("select * from product inner join productcategory on product.product_productcategory = productcategory.productcategory_id inner join supplier on product.product_supplier = supplier.supplier_id  where product_productcategory = %d;", productCategory.getId());
        List<Product> productsByCat = new ArrayList<Product>();


        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                ProductCategory productCat= new ProductCategory(
                        resultSet.getInt("productcategory_id"),
                        resultSet.getString("productcategory_name"),
                        resultSet.getString("productcategory_department"),
                        resultSet.getString("productcategory_description"));
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));
                Product target = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("product_defaultprice"),
                        resultSet.getString("product_defaultcurrency"),
                        resultSet.getString("product_description"),
                        productCat, supplier);
                productsByCat.add(target);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsByCat;
    }
}
