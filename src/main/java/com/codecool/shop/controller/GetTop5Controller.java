package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class GetTop5Controller {

    private static final Logger logger = LoggerFactory.getLogger(GetTop5Controller.class);
    private static final String SERVICE_URL = "http://localhost:60003/api/";
    private static final String API_KEY = "negy";

    public static boolean isRunning() throws URISyntaxException, IOException {
        logger.info("Checking server is running");
        Boolean running = execute("/status").equalsIgnoreCase("ok");
        if (running) {
            logger.info("Service is running");
        } else {
            logger.warn("Service is not running");
        }
        return running;
    }

    public static List<Integer> getTop5(spark.Request request, Response response) throws IOException, URISyntaxException {
        JSONArray array = new JSONArray(execute("/gettop5"));
        List<Integer> top5Product = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            top5Product.add(array.getJSONObject(i).getInt("productID"));
        }
        return top5Product;
    }

    public static String sendProduct(spark.Request request, Response response) throws IOException, URISyntaxException {



        if (request.session().attribute("cart") != null) {
            Cart cart = request.session().attribute("cart");

            for (LineItem lineItem : cart.getLineItems()) {
                System.out.println(lineItem.toString());
            }
            response.redirect("/");
        }

        request.session().removeAttribute("cart");
        StringEntity js = new StringEntity(request.session().attribute("cart"));


        return Request.Post(SERVICE_URL + API_KEY + "/addproduct").body(js)
                .execute().returnContent().asString();
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + API_KEY + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }

}