package com.inkostilaton.dbpractice;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends DataAdapter {
    VisibilityList<Product> products = new VisibilityList<>();

    public ProductAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView, R.layout.item_product);
        products.add(new Product("Chair", "Furniture", "17.01.20", "..."));
    }

    public class Product implements VisibilityList.Searchable {
        String name;
        String type;
        String startDate;
        String endDate;

        public Product(String name, String type, String startDate, String endDate) {
            this.name = name;
            this.type = type;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public boolean match(String query) {
            return name.toLowerCase().contains(query);
        }
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView startDate;
        TextView endDate;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            type = itemView.findViewById(R.id.product_type);
            startDate = itemView.findViewById(R.id.product_start);
            endDate = itemView.findViewById(R.id.product_end);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(View view) {
        return new ProductViewHolder(view);
    }

    @Override
    public VisibilityList getData() {
        return products;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBindSwipeViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Product product = products.get(i);
        ProductViewHolder holder = (ProductViewHolder) viewHolder;
        holder.name.setText(product.name);
        holder.type.setText(product.type);
        holder.startDate.setText(product.startDate);
        holder.endDate.setText(product.endDate);
    }
}
