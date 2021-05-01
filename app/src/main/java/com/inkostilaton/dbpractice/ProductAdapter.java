package com.inkostilaton.dbpractice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.inkostilaton.dbpractice.Database.PRODUCT;
import static com.inkostilaton.dbpractice.MainActivity.database;

public class ProductAdapter extends DataAdapter {

    VisibilityList<Product> products;

    public ProductAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView, R.layout.item_product);
    }

    public class Product implements VisibilityList.Searchable {
        int id;
        String name;
        String type;
        String startDate;
        String endDate;

        public Product(int id, String name, String type, String startDate, String endDate) {
            this.id = id;
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
        products = new VisibilityList<>();

        String queryString = "SELECT * FROM " + PRODUCT;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int prod_id = cursor.getInt(0);
                String name = cursor.getString(1);
                String type = cursor.getString(2);
                String startDate = cursor.getString(3);
                String endDate = cursor.getString(4);

                Product newProduct =  new Product(prod_id, name, type, startDate, endDate);
                products.add(newProduct);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
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

    @Override
    protected void delete(int index) {
        Product removableProduct = products.get(index);
        database.deleteProduct(removableProduct.id);
    }
}
