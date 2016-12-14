package com.codecool.shop.controller;



import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;



public class PaymentController {

    public static ModelAndView renderPayment(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        return new ModelAndView(params, "product/payment");
    }

}