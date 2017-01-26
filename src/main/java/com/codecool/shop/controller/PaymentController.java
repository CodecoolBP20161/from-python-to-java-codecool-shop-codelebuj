package com.codecool.shop.controller;


import com.codecool.shop.model.CartImpl;
import com.codecool.shop.model.Order;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;



public class PaymentController {

    private static final String SERVICE_URL = "http://localhost:65011/shipping-cost?origin=budapest&destination=";


    public static ModelAndView renderPayment(spark.Request req, Response res) throws IOException, URISyntaxException {
        Map params = ProductController.renderParams(req, res);
        Order customerDetails = req.session().attribute("order");
        String destination = customerDetails.getShippingAddress().getCity();
        CartImpl cart = req.session().attribute("cart");

        JSONObject jsonObject = new JSONObject(execute(destination));
        int shippingfee = jsonObject.getJSONObject("expressCourier").getInt("cost");
        int distance = jsonObject.getJSONObject("expressCourier").getInt("distanceInKm");
        String currency = jsonObject.getJSONObject("expressCourier").getString("currency");
        String details = jsonObject.getJSONObject("expressCourier").getString("details");

        System.out.println( + cart.totalPrice());
        params.put("totalPrice", shippingfee + cart.totalPrice());
        params.put("shippingfee", shippingfee);
        params.put("distance",distance);
        params.put("currency", currency);
        params.put("details", details);
        params.put("fname", customerDetails.getFirstName());
        params.put("lname", customerDetails.getLastName());
        params.put("email", customerDetails.getEmail());
        params.put("phonenumber", customerDetails.getPhoneNumber());
        params.put("sacountry", customerDetails.getShippingAddress().getCountry());
        params.put("sacity", customerDetails.getShippingAddress().getCity());
        params.put("sazipcode", customerDetails.getShippingAddress().getZipCode());
        params.put("saaddress", customerDetails.getShippingAddress().getAddressInfo());
        params.put("bacountry", customerDetails.getBillingAddress().getCountry());
        params.put("bacity", customerDetails.getBillingAddress().getCity());
        params.put("bazipcode", customerDetails.getBillingAddress().getZipCode());
        params.put("baaddress", customerDetails.getBillingAddress().getAddressInfo());

        return new ModelAndView(params, "product/payment");
    }


    private static String execute(String urlDestination) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + urlDestination ).build();
        return Request.Get(uri).execute().returnContent().asString();
    }

}