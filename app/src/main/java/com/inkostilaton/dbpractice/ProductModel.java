package com.inkostilaton.dbpractice;

public class ProductModel {

    int prod_id;
    String name;
    String type;
    String startDate;
    String endDate;

    public ProductModel(int prod_id, String name, String type, String startDate, String endDate) {
        this.prod_id = prod_id;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getProd_id() {
        return prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
