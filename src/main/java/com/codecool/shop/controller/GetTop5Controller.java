package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJdbcImpl;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private static final String SERVICE_URL = "http://localhost:60001/api/";
    private static final String API_KEY = "";

    static private ProductDao productDataStore = ProductDaoJdbcImpl.getInstance();


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

    public static List<Product> getTop5(spark.Request req, Response response) throws IOException, URISyntaxException {
        Product product;
        List<Product> top5Product = new ArrayList<>();
        JSONArray array = new JSONArray(execute("/gettop5"));
        for (int i = 0; i < array.length(); i++) {
            product = productDataStore.find(array.getJSONObject(i).getInt("productID"));
            top5Product.add(product);
        }
        return top5Product;
    }

    public static String sendProduct(spark.Request request, Response response) throws IOException, URISyntaxException {

        org.json.simple.JSONArray array = new org.json.simple.JSONArray();
        Cart cart = request.session().attribute("cart");

        if (cart != null) {
            for (LineItem lineItem : cart.getLineItems()) {
                JSONObject item = new JSONObject();
                item.put("id", lineItem.getProduct().getId());
                item.put("quantity", lineItem.getQuantity());
                array.add(item);
            }
        }
        StringEntity js = new StringEntity(array.toString());

        Request.Post(SERVICE_URL + API_KEY + "/addproduct").body(js).execute();

        return "";
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + API_KEY + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }

}