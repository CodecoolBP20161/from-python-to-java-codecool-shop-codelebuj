package com.codecool.shop.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {


    public static ArrayList<String> readJsonFromFile() {

        JSONParser parser = new JSONParser();

        ArrayList<String> databaseInfo = null;

        try {

            Object obj = parser.parse(new FileReader("login.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String url = (String) jsonObject.get("url");
            //System.out.println(url);

            String database = (String) jsonObject.get("database");
            //System.out.println(database);

            String user = (String) jsonObject.get("user");
            //System.out.println(user);

            String password = (String) jsonObject.get("password");
            //System.out.println(password);

            databaseInfo.add(url);
            databaseInfo.add(database);
            databaseInfo.add(user);
            databaseInfo.add(password);
            System.out.println(databaseInfo);


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
