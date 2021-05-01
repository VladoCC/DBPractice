package com.inkostilaton.dbpractice;

public class CustomModel {

    private int cust_id;
    private String name;
    private String postal;
    private String state;
    private String city;
    private String address;

    public CustomModel(int cust_id, String name, String postal, String state, String city, String address) {
        this.cust_id = cust_id;
        this.name = name;
        this.postal = postal;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    public int getCust_id() {
        return cust_id;
    }

    public String getName() {
        return name;
    }

    public String getPostal() {
        return postal;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

}
