package com.inkostilaton.dbpractice;

public class ProductListModel {

    int order_id;
    String product_name;

    public ProductListModel(int order_id, String product_name) {
        this.order_id = order_id;
        this.product_name = product_name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getProduct_name() {
        return product_name;
    }
}
