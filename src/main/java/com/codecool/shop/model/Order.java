package com.codecool.shop.model;

public class Order {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private Address billingAddress;
    private Address shippingAddress;


    public Order(int id, String firstName, String lastName, String email, int phoneNumber, Address billingAddress, Address shippingAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }
}