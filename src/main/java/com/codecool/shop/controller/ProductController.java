package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    static private ProductDao productDataStore = ProductDaoMem.getInstance();
    static private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    static private SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

    public static ModelAndView renderProductsByCategory(Request req, Response res){
        int categoryId = Integer.parseInt(req.params(":category_id"));

        Map params = new HashMap<>();
        params.put("mainLabel", productCategoryDataStore.find(categoryId).getName());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productCategoryDataStore.find(categoryId).getProducts());
        return new ModelAndView(params, "product/index");


    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res){
        int supplierId = Integer.parseInt(req.params(":supplier_id"));

        Map params = new HashMap<>();
        params.put("mainLabel", productSupplierDataStore.find(supplierId).getName());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productSupplierDataStore.find(supplierId).getProducts());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAllProducts(Request req, Response res) {

        Map params = new HashMap<>();
        params.put("mainLabel", "All Products");
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

}
