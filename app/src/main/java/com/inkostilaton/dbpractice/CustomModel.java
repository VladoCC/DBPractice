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

    public CustomModel() {
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
