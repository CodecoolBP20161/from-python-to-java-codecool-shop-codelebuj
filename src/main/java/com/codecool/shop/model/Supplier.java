package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;


public class Supplier extends BaseModel {
    private List<Product> products;

    public Supplier(String name, String description) {
        super(name);
        this.description = description;
        this.products = new ArrayList<>();
    }

    public Supplier(Integer id, String name, String description) {
        super(name, description);
        this.id = id;
        this.products = new ArrayList<>();
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

        Supplier that = (Supplier) o;

        return id == that.id;
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}