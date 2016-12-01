package com.codecool.shop.util;


public class IdGenerator {

    private static IdGenerator instance = null;

    int currentID = 0;

    private IdGenerator() {

    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public int getNextId(){
        return currentID += 1;

    }


}