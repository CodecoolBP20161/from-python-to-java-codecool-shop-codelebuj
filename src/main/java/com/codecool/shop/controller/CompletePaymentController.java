package com.codecool.shop.controller;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;

public class CompletePaymentController {

    public static ModelAndView renderCompletePayment(Request req, Response res){
        req.session().invalidate();
        Map params = ProductController.renderParams(req, res);
        return new ModelAndView(params, "product/thankyou");
    }

}