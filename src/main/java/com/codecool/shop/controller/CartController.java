package com.codecool.shop.controller;

import com.codecool.shop.model.CartImpl;
import com.codecool.shop.model.Cart;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

/**
 * Created by levente on 2016.11.16..
 */
public class CartController {

    public static ModelAndView renderCart(Request req, Response res){
        Cart cart = req.session().attribute("cart");
        cart.
        return ModelAndView(params, "product/index");
    }

    public static ModelAndView addToCart(Request req, Response res){
        Cart cart = new CartImpl();
        cart.addProduct( product );
        req.session().attribute("cart", cart);

        return ModelAndView(params, "product/index");
    }

}
