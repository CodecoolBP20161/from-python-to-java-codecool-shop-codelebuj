package com.codecool.shop.controller;



import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.AddressDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.util.IdGenerator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;



public class CheckoutController {

    private static OrderDao orderDataStore = OrderDaoJdbc.getInstance();
    private static AddressDao adressDataStore = AddressDaoJdbc.getInstance();

    public static ModelAndView renderCheckout(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        return new ModelAndView(params, "product/checkout");
    }

    public static String constructorOrder(Request req, Response res){
        int orderId = IdGenerator.getInstance().getNextId();
        int billaddressId = IdGenerator.getInstance().getNextId();
        int shippaddressId = IdGenerator.getInstance().getNextId();

        String firstName = req.queryParams("fname");
        String lastName = req.queryParams("lname");
        String email = req.queryParams("email");
        String phoneNumber = req.queryParams("phonenumber");
        String billingCountry = req.queryParams("bacountry");
        String billingCity = req.queryParams("bacity");
        String billingZip = req.queryParams("bazipcode");
        String billingAddressInfo = req.queryParams("baaddress");
        String checkb = req.queryParams("checkb");
        String shippingCountry = req.queryParams("sacountry");
        String shippingCity = req.queryParams("sacity");
        String shippingZip = req.queryParams("sazipcode");
        String shippingAddressInfo = req.queryParams("saaddress");

        Address billingaddress = new Address(billaddressId,billingCountry,billingCity,billingZip,billingAddressInfo);
        adressDataStore.add(billingaddress);
        Address shippingaddress = null;
        if (checkb != null){
            shippingaddress = new Address(billaddressId,billingCountry,billingCity,billingZip,billingAddressInfo);
            adressDataStore.add(shippingaddress);
        }else {
            shippingaddress = new Address(shippaddressId, shippingCountry, shippingCity, shippingZip, shippingAddressInfo);
            adressDataStore.add(shippingaddress);
        }
        Order order = new Order(orderId, firstName, lastName, email, phoneNumber, billingaddress, shippingaddress);
        orderDataStore.add(order);
        res.redirect("/payment");
        return null;
    }

}