package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



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

    public static String getTop5(spark.Request request, Response response) throws IOException, URISyntaxException {
        return execute("/gettop5");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + API_KEY + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }

}