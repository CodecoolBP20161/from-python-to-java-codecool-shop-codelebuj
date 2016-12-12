package com.codecool.shop.model;

public class Address {
    private int id;
    public Address(int id, String country, String city, int zipCode, String addressInfo) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.addressInfo = addressInfo;
    }
    private String country;
    private String city;
    private int zipCode;
    private String addressInfo;
}