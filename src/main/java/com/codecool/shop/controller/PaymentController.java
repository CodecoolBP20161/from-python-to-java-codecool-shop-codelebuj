package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Iterator;
import java.util.Map;



public class PaymentController {

    static private OrderDao orderDataStore = OrderDaoJdbc.getInstance();

    public static ModelAndView renderPayment(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        Order customerDetails = req.session().attribute("order");
        System.out.println(customerDetails.getShippingAddress().getCity());
//        int orderId = cu
        params.put("fname", customerDetails.getFirstName());
        params.put("lname", customerDetails.getLastName());
        params.put("email", customerDetails.getEmail());
        params.put("phonenumber", customerDetails.getPhoneNumber());
        params.put("bacountry", customerDetails.getBillingAddress().getCountry());
        params.put("bacity", customerDetails.getBillingAddress().getCity());
        params.put("bazipcode", customerDetails.getBillingAddress().getZipCode());
        params.put("baaddress", customerDetails.getBillingAddress().getAddressInfo());
        params.put("sacountry", customerDetails.getShippingAddress().getCountry());
        params.put("sacity", customerDetails.getShippingAddress().getCity());
        params.put("sazipcode", customerDetails.getShippingAddress().getZipCode());
        params.put("saaddress", customerDetails.getShippingAddress().getAddressInfo());

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove();
        }
        return new ModelAndView(params, "product/payment");
    }

}