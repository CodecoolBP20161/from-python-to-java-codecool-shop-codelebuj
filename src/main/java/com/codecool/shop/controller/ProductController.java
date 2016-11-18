package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;

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

    public static int getCartQuantity(Request req) {
        int totalQuantity = 0;
        Cart c = req.session().attribute("cart");
        if ( c != null ) {
            totalQuantity = c.getTotalQuantity();
        }
        return totalQuantity;
    }


    public static ModelAndView renderProductsByCategory(Request req, Response res){
        int categoryId = Integer.parseInt(req.params(":category_id"));

        Map params = new HashMap<>();
        params.put("mainLabel", productCategoryDataStore.find(categoryId).getName());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productCategoryDataStore.find(categoryId).getProducts());
        params.put("cartquantity", getCartQuantity(req));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res){
        int supplierId = Integer.parseInt(req.params(":supplier_id"));

        Map params = new HashMap<>();
        params.put("mainLabel", productSupplierDataStore.find(supplierId).getName());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productSupplierDataStore.find(supplierId).getProducts());
        params.put("cartquantity", getCartQuantity(req));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAllProducts(Request req, Response res) {

        Map params = new HashMap<>();
        params.put("mainLabel", "All Products");
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("cartquantity", getCartQuantity(req));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCart(Request req,Response res){
        Map params = new HashMap<>();

        params.put("cartp",req.session().attribute("cart"));
        params.put("cartquantity", getCartQuantity(req));
        return new ModelAndView(params, "product/shoppingcart");
    }

}
