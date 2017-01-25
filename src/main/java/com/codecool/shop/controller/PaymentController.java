package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;



public class PaymentController {

    public static ModelAndView renderPayment(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        Order customerDetails = req.session().attribute("order");

        params.put("fname", customerDetails.getFirstName());
        params.put("lname", customerDetails.getLastName());
        params.put("sacountry", customerDetails.getShippingAddress().getCountry());
        params.put("sacity", customerDetails.getShippingAddress().getCity());
        params.put("sazipcode", customerDetails.getShippingAddress().getZipCode());
        params.put("saaddress", customerDetails.getShippingAddress().getAddressInfo());

        return new ModelAndView(params, "product/payment");
    }

}