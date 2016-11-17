package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartImpl;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;


public class CartController {

    public static ModelAndView renderCart(Request req, Response res){
        Cart cart = req.session().attribute("cart");
        HashMap params = new HashMap();
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView addToCart(Request req, Response res){
        int productId = Integer.parseInt(req.params(":product_id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(productId);
        Cart cart = (Cart) req.session().attribute("cart");

        if (cart == null) {
            cart = new CartImpl();
            req.session().attribute("cart", cart);
        }
        cart.addProduct(product);
        req.session().attribute("cart", cart);
        res.redirect("/");
        return null;
    }

}
