package com.codecool.shop.controller;



import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.AddressDaoJdbc;
import com.codecool.shop.dao.implementation.LineItemDaoJdbcImpl;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;



public class CheckoutController {

    private static OrderDao orderDataStore = OrderDaoJdbc.getInstance();
    private static AddressDao adressDataStore = AddressDaoJdbc.getInstance();
    private static LineItemDao lineItemDataStore = LineItemDaoJdbcImpl.getInstance();

    public static ModelAndView renderCheckout(Request req, Response res){
        Map params = ProductController.renderParams(req, res);
        return new ModelAndView(params, "product/checkout");
    }
//Gather the data from html form
    public static String constructorOrder(Request req, Response res){
//        int orderId = IdGenerator.getInstance().getNextId();
//        int billaddressId = IdGenerator.getInstance().getNextId();
//        int shippaddressId = IdGenerator.getInstance().getNextId();

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

//create address instance
//        Address billingaddress = new Address(billaddressId,billingCountry,billingCity,billingZip,billingAddressInfo);
        Address billingaddress = new Address(billingCountry,billingCity,billingZip,billingAddressInfo);

        adressDataStore.add(billingaddress);
        Address shippingaddress = null;
//         depending on weather the checkbox checked or not, we create shipping address,
//         then insert into the address table
        if (checkb != null){
//            shippingaddress = new Address(billaddressId,billingCountry,billingCity,billingZip,billingAddressInfo);
            shippingaddress = new Address(billingCountry,billingCity,billingZip,billingAddressInfo);

            adressDataStore.add(shippingaddress);
        }else {
            shippingaddress = new Address(shippingCountry, shippingCity, shippingZip, shippingAddressInfo);
            adressDataStore.add(shippingaddress);
        }
        // create order instance with addresses
//        Order order = new Order(orderId, firstName, lastName, email, phoneNumber, billingaddress, shippingaddress);
        Order order = new Order(firstName, lastName, email, phoneNumber, billingaddress, shippingaddress);

        orderDataStore.add(order);

        // get the session to see what the user added to the cart so far
        Cart cart = req.session().attribute("cart");
        // Insert lineitems with order Id into lineitem table
        for (LineItem lineItem : cart.getLineItems()){
            lineItemDataStore.add(lineItem, order);
        }
        GetTop5Controller x = new GetTop5Controller();

        try {
            x.sendProduct(req,res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        res.redirect("/payment");
        return null;
    }

}
