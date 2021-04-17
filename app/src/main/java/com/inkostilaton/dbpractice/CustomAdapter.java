package com.inkostilaton.dbpractice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.inkostilaton.dbpractice.Database.CUSTOMER;
import static com.inkostilaton.dbpractice.MainActivity.database;

public class CustomAdapter extends DataAdapter {

    private VisibilityList<Customer> customers;

    public CustomAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView, R.layout.item_custom);
    }

    @Override
    protected void initData() {
        customers = new VisibilityList<>();

        String queryString = "SELECT * FROM " + CUSTOMER;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int cust_id = cursor.getInt(0);
                String name = cursor.getString(1);
                String postal = cursor.getString(2);
                String state = cursor.getString(3);
                String city = cursor.getString(4);
                String address = cursor.getString(5);

                Customer newCustomer =  new Customer(name, postal + state + city + address);
                customers.add(newCustomer);
            }
            while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
    }

    private class Customer implements VisibilityList.Searchable {
        String name;
        String address;

        public Customer(String name, String address) {
            this.name = name;
            this.address = address;
        }

        @Override
        public boolean match(String query) {
            return name.toLowerCase().contains(query);
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.custom_name);
            address = view.findViewById(R.id.custom_address);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(View view) {
        return new CustomViewHolder(view);
    }

    @Override
    public VisibilityList getData() {
        return customers;
    }

    @Override
    public void onBindSwipeViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Customer customer = customers.get(i);
        CustomViewHolder holder = (CustomViewHolder) viewHolder;
        holder.name.setText(customer.name);
        holder.address.setText(customer.address);
    }
}
