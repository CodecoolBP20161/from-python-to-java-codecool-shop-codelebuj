package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    static private ProductDao productDataStore = ProductDaoJdbcImpl.getInstance();
    static private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
    static private SupplierDao productSupplierDataStore = SupplierDaoJdbc.getInstance();

    public static int getCartQuantity(Request req) {
        int totalQuantity = 0;
        Cart c = req.session().attribute("cart");
        if (c != null) {
            totalQuantity = c.getTotalQuantity();
        }
        return totalQuantity;
    }

    public static Map renderParams(Request req, Response res) {
        Map params = new HashMap();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("cartquantity", getCartQuantity(req));
        try {
            params.put("top5", GetTop5Controller.getTop5(req, res));
        } catch (IOException e) {
            logger.info("Is empty");
        } catch (URISyntaxException e) {
            logger.info("URI not good");
        }
        return params;
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        int categoryId = Integer.parseInt(req.params(":category_id"));
        Map params = renderParams(req, res);
        params.put("mainLabel", productCategoryDataStore.find(categoryId).getName());
        params.put("products", productCategoryDataStore.find(categoryId).getProducts());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        int supplierId = Integer.parseInt(req.params(":supplier_id"));
        Map params = renderParams(req, res);
        params.put("mainLabel", productSupplierDataStore.find(supplierId).getName());
        params.put("products", productSupplierDataStore.find(supplierId).getProducts());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAllProducts(Request req, Response res) {
        Map params = renderParams(req, res);
        params.put("mainLabel", "All Products");
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCart(Request req, Response res) {

        Map params = renderParams(req, res);
        if (req.session().attribute("cart") == null) {
            Cart cart = new CartImpl();
            params.put("cartp", cart);
            return new ModelAndView(params, "product/shoppingcart");
        } else {
            params.put("cartp", req.session().attribute("cart"));

            return new ModelAndView(params, "product/shoppingcart");
        }

    }


}
