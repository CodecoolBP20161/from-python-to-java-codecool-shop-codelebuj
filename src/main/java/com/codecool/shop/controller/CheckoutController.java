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
        int phoneNumber = Integer.parseInt(req.params("phoneNumber"));
        String billingCountry = req.params("bacountry");
        String billingCity = req.params("bacity");
        int billingZip = Integer.parseInt(req.params("bazipcode"));
        String billingAddressInfo = req.params("baaddress");
        String shippingCountry = req.params("sacountry");
        String shippingCity = req.params("sacity");
        int shippingZip = Integer.parseInt(req.params("sazipcode"));
        String shippingAddressInfo = req.params("saaddress");


        Address billingaddress = new Address(1,billingCountry,billingCity,billingZip,billingAddressInfo);
        Address shippingaddress = new Address(2,shippingCountry,shippingCity,shippingZip,shippingAddressInfo);
        Order order = new Order(3, firstName, lastName, email, phoneNumber, billingaddress, shippingaddress);
        orderDataStore.add(order);
        res.redirect("/payment");
        return null;
    }


}
