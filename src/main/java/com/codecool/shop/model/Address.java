package com.codecool.shop.model;

public class Address {
    private int id;
    private String country;
    private String city;
    private String zipCode;
    private String addressInfo;

    public Address( String country, String city, String zipCode, String addressInfo) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.addressInfo = addressInfo;
    }

    public Address( int id, String country, String city, String zipCode, String addressInfo) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.addressInfo = addressInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddressInfo() {
        return addressInfo;
    }
}