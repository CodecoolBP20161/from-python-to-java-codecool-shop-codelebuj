package com.codecool.shop.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {


    public static HashMap<String ,String> readJsonFromFile() {
        JSONParser parser = new JSONParser();
        HashMap<String,String> databaseInfo = new HashMap();
        try {
            Object obj = parser.parse(new FileReader("login.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String url = (String) jsonObject.get("url");
            String database = (String) jsonObject.get("database");
            String user = (String) jsonObject.get("user");
            String password = (String) jsonObject.get("password");

            databaseInfo.put("url",url);
            databaseInfo.put("database", database);
            databaseInfo.put("user",user);
            databaseInfo.put("password", password);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return databaseInfo;
    }



}
