package com.inkostilaton.dbpractice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.inkostilaton.dbpractice.Database.EMPLOYEE;
import static com.inkostilaton.dbpractice.Database.ORDERS;
import static com.inkostilaton.dbpractice.MainActivity.database;

public class OrderAdapter extends DataAdapter {
    VisibilityList<Order> orders;

    public OrderAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView, R.layout.item_order);
    }

    public class Order implements VisibilityList.Searchable {
        String customer;
        String status;
        String emp;
        String startDate;
        String endDate;
        int sum;

        String[] products;
        Transaction[] transactions;

        public Order(String customer, String status, String emp, String startDate, String endDate, int sum, String[] products, Transaction[] transactions) {
            this.customer = customer;
            this.status = status;
            this.emp = emp;
            this.startDate = startDate;
            this.endDate = endDate;
            this.sum = sum;
            this.products = products;
            this.transactions = transactions;
        }

        @Override
        public boolean match(String query) {
            return customer.toLowerCase().contains(query);
        }
    }

    public class Transaction {
        String date;
        int value;

        public Transaction(String date, int value) {
            this.date = date;
            this.value = value;
        }
    }

    private class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView customer;
        TextView status;
        TextView emp;
        TextView startDate;
        TextView endDate;
        TextView sum;

        private LinearLayout products;
        private LinearLayout transactions;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            customer = itemView.findViewById(R.id.order_cust_name);
            status = itemView.findViewById(R.id.order_status);
            emp = itemView.findViewById(R.id.order_emp);
            startDate = itemView.findViewById(R.id.order_start);
            endDate = itemView.findViewById(R.id.order_end);
            sum = itemView.findViewById(R.id.order_sum);

            products = itemView.findViewById(R.id.order_prod_layout);
            transactions = itemView.findViewById(R.id.order_trans_layout);
        }

        public void addProduct(String name) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_product, null);
            TextView nameText = view.findViewById(R.id.order_prod_name);
            nameText.setText(name);
            products.addView(view);
        }

        public void addTransaction(int value, String date) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_transaction, null);
            TextView valueText = view.findViewById(R.id.order_trans_sum);
            valueText.setText("" + value);
            TextView dateText = view.findViewById(R.id.order_trans_date);
            dateText.setText(date);
            transactions.addView(view);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(View view) {
        return new OrderViewHolder(view);
    }

    @Override
    public VisibilityList getData() {
        return orders;
    }

    @Override
    protected void initData() {
        orders = new VisibilityList<>();

        String queryString = "SELECT * FROM " + ORDERS;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int order_id = cursor.getInt(0);
                String customer = cursor.getString(1);
                String status = cursor.getString(2);
                String emp = cursor.getString(3);
                String startDate = cursor.getString(4);
                String endDate = cursor.getString(5);
                String sum = cursor.getString(6);

                TransactionModel[] modelArray = database.getTransactionsOfOrder(order_id);
                int len = modelArray.length;
                Transaction[] transactionArray = new Transaction[len];
                for (int i = 0; i < len; i++) {
                    Transaction newTransaction = new Transaction(modelArray[i].date, modelArray[i].value);
                    transactionArray[i] = newTransaction;
                }
                Order newOrder =  new Order(customer, status, emp, startDate, endDate, Integer.parseInt(sum), database.getProductListOfOrder(order_id), transactionArray);
                orders.add(newOrder);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    @Override
    public void onBindSwipeViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Order order = orders.get(i);
        OrderViewHolder holder = (OrderViewHolder) viewHolder;
        holder.customer.setText(order.customer);
        holder.status.setText(order.status);
        holder.emp.setText(order.emp);
        holder.startDate.setText(order.startDate);
        holder.endDate.setText(order.endDate);
        holder.sum.setText(order.sum + "");

        holder.products.removeAllViews();
        for (String product: order.products) {
            holder.addProduct(product);
        }

        holder.transactions.removeAllViews();
        for (Transaction transaction: order.transactions) {
            holder.addTransaction(transaction.value, transaction.date);
        }
    }
}
