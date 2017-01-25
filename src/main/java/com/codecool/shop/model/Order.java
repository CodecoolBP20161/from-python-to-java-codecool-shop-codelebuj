package com.codecool.shop.model;

public class Order {
    private static int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address billingAddress;
    private Address shippingAddress;


    public Order(String firstName, String lastName, String email, String phoneNumber, Address billingAddress, Address shippingAddress) {
        this.id = ++id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public Order(int id,String firstName, String lastName, String email, String phoneNumber, Address billingAddress, Address shippingAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }
}