package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
    private String department;
    private List<Product> products;

    public ProductCategory(Integer id, String name, String department, String description) {
        this(name, department, description);
        this.id = id;
    }

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return department != null ? department.hashCode() : 0;
    }

    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}