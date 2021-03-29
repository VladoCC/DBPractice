package com.inkostilaton.dbpractice;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends DataAdapter {

    private VisibilityList<Customer> customers = new VisibilityList<>();

    public CustomAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView, R.layout.item_custom);
        customers.add(new Customer("Sam Right", "111555, Prim, Vladivostok, test"));
        customers.add(new Customer("Jim Wrong", "111555, Prim, Vladivostok, test"));
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
