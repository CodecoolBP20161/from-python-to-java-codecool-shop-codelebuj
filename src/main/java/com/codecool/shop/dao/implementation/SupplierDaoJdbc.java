package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.IdGenerator;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SupplierDaoJdbc extends ConnectionDb implements SupplierDao {

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }

    private static SupplierDaoJdbc instance = null;

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier category) {
        try {
            int id = IdGenerator.getInstance().getNextId();
            category.setId(id);
            String query = "INSERT INTO supplier (supplier_id, supplier_name, supplier_description) VALUES (?,?,?);";
            PreparedStatement safeInput = getConnection().prepareStatement(query);
            safeInput.setInt(1,category.getId());
            safeInput.setString(2,category.getName());
            safeInput.setString(3,category.getDescription());
            safeInput.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE supplier_id = "+ id+ ";";
        Supplier found = null;
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                ProductDaoJdbcImpl productDaoJdbc = new ProductDaoJdbcImpl();
                found = new Supplier(resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));

                found.setProducts(productDaoJdbc.getBy(found));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public void remove(int id) {
        String query = String.format("DELETE FROM supplier WHERE supplier_id = %d;", id);
        executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier;";
        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Supplier actTodo = new Supplier(resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_description"));
                resultList.add(actTodo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
