package com.inkostilaton.dbpractice;

public class TransactionModel {

    String date;
    int value;
    int order_id;

    public TransactionModel(String date, int value, int order_id) {
        this.date = date;
        this.value = value;
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public int getOrder_id() {
        return order_id;
    }
}
