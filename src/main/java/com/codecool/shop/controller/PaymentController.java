package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.model.Order;
import com.codecool.shop.util.IdGenerator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.swing.*;
import java.util.Map;



public class PaymentController {

    static private OrderDao orderDataStore = OrderDaoJdbc.getInstance();

    public static ModelAndView renderPayment(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        int id = req.session().attribute("order_id");
        int orderId = orderDataStore.find(id).getId();
        params.put("fname", orderDataStore.find(orderId).getFirstName());
        params.put("lname", orderDataStore.find(orderId).getLastName());
        params.put("email", orderDataStore.find(orderId).getEmail());
        params.put("phonenumber", orderDataStore.find(orderId).getPhoneNumber());
        params.put("bacountry", orderDataStore.find(orderId).getBillingAddress().getCountry());
        params.put("bacity", orderDataStore.find(orderId).getBillingAddress().getCity());
        params.put("bazipcode", orderDataStore.find(orderId).getBillingAddress().getZipCode());
        params.put("baaddress", orderDataStore.find(orderId).getBillingAddress().getAddressInfo());
        params.put("sacountry", orderDataStore.find(orderId).getBillingAddress().getCountry());
        params.put("sacity", orderDataStore.find(orderId).getBillingAddress().getCity());
        params.put("sazipcode", orderDataStore.find(orderId).getBillingAddress().getZipCode());
        params.put("saaddress", orderDataStore.find(orderId).getBillingAddress().getAddressInfo());
        return new ModelAndView(params, "product/payment");
    }

}