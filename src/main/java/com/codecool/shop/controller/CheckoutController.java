package com.codecool.shop.controller;



import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;



public class CheckoutController {

    public static ModelAndView renderCheckout(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        return new ModelAndView(params, "product/checkout");
    }

}
