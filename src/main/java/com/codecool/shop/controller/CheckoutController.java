package com.codecool.shop.controller;



import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;



public class CheckoutController {

    static private OrderDao orderDataStore = OrderDaoJdbc.getInstance();

    public static ModelAndView renderCheckout(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        return new ModelAndView(params, "product/checkout");
    }

    public static String constructorOrder(Request req, Response res){
        String firstName = req.params("fname");
        String lastName = req.params("lname");
        String email = req.params("email");
        String phoneNumber = req.params("phoneNumber");
        String billingCountry = req.params("bacountry");
        String billingCity = req.params("bacity");
        String billingZip = req.params("bazipcode");
        String billingAddressInfo = req.params("baaddress");
        String shippingCountry = req.params("sacountry");
        String shippingCity = req.params("sacity");
        String shippingZip = req.params("sazipcode");
        String shippingAddressInfo = req.params("saaddress");


        Address billingaddress = new Address();
        Address shippingaddress = new Address();
        Order order = new Order(id, firstName, lastName, email, phoneNumber, billingaddress, shippingaddress);
        orderDataStore.add(order);
        res.redirect("/payment");
        return null;
    }


}
