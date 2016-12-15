package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJdbcImpl;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartImpl;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;


public class CartController {


    private static Cart getCartItem(Request req){
        Cart cart = req.session().attribute("cart");
        return cart;
    }

    private static Product getProduct(Request req){
        int productId = Integer.parseInt(req.params(":product_id"));
        ProductDao productDataStore = ProductDaoJdbcImpl.getInstance();
        Product product = productDataStore.find(productId);
        return product;
    }

    public static ModelAndView renderCart(Request req, Response res){
        Cart cart = req.session().attribute("cart");
        HashMap params = new HashMap();
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView addToCart(Request req, Response res){
        Cart cart = getCartItem(req);
        Product product = getProduct(req);
        if (cart == null) {
            cart = new CartImpl();
            req.session().attribute("cart", cart);
        }
        cart.addProduct(product);
        req.session().attribute("cart", cart);
        res.redirect("/");
        return null;
    }

    public static ModelAndView increaseCartItem(Request req, Response res){
        Cart cart = getCartItem(req);
        Product product = getProduct(req);
        cart.addProduct(product);
        req.session().attribute("cart", cart);
        res.redirect("/shoppingcart");
        return null;
    }

    public static ModelAndView decreaseCartItem(Request req, Response res){
        Cart cart = getCartItem(req);
        if (cart != null) {
            Product product = getProduct(req);
            cart.removeProduct(product);
            req.session().attribute("cart", cart);
            res.redirect("/shoppingcart");
        }
        else {
            cart = new CartImpl();
            req.session().attribute("cart", cart);
            res.redirect("/shoppingcart");
        }
        return null;
    }

    public static ModelAndView deleteCartItem(Request req, Response res){
        Cart cart = getCartItem(req);
        Product product = getProduct(req);
        cart.deleteAllProduct(product);
        req.session().attribute("cart", cart);
        res.redirect("/shoppingcart");
        return null;

    }


}
