package com.inkostilaton.dbpractice;

public class OrderModel {

    int order_id;
    String customer;
    String status;
    String emp;
    String startDate;
    String endDate;
    String sum;

    String[] products;
    TransactionModel[] transactions;

    public OrderModel(int order_id, String customer, String status, String emp, String startDate, String endDate, String sum, String[] products, TransactionModel[] transactions) {
        this.order_id = order_id;
        this.customer = customer;
        this.status = status;
        this.emp = emp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sum = sum;
        this.products = products;
        this.transactions = transactions;
        }

    public String getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public String getEmp() {
        return emp;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSum() {
        return sum;
    }

}
