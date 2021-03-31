package com.inkostilaton.dbpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditOrderActivity extends EditActivity {

    private EditText status;
    private EditText sum;

    private CalendarView startDate;
    private CalendarView endDate;

    private Spinner custom;
    private Spinner emp;

    private ImageButton addProduct;
    private ImageButton addTransaction;

    private LinearLayout product;
    private LinearLayout transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        status = findViewById(R.id.add_order_status);
        sum = findViewById(R.id.add_order_sum);

        startDate = findViewById(R.id.add_order_start);
        endDate = findViewById(R.id.add_order_end);

        custom = findViewById(R.id.add_order_custom_select);
        emp = findViewById(R.id.add_order_emp_select);

        addProduct = findViewById(R.id.add_order_product_new);
        addTransaction = findViewById(R.id.add_order_transaction_new);

        product = findViewById(R.id.add_order_product_layout);
        transaction = findViewById(R.id.add_order_transaction_layout);

        startDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                view.setDate(calendar.getTimeInMillis());
            }
        });
        endDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                view.setDate(calendar.getTimeInMillis());
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditOrderActivity.this);
                builder.setTitle("Products");
                builder.setItems(getProductList(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Map<String, String> productMap = getProduct(i);
                        View view = LayoutInflater.from(EditOrderActivity.this).inflate(R.layout.list_item_product, null);
                        final View parent = LayoutInflater.from(EditOrderActivity.this).inflate(R.layout.remove_layout, null);
                        TextView productText = view.findViewById(R.id.order_prod_name);
                        productText.setText(productMap.get("name"));
                        LinearLayout include = parent.findViewById(R.id.elem_include);
                        include.addView(view);
                        final int pos = transaction.getChildCount();
                        ImageView image = parent.findViewById(R.id.elem_delete);
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((ViewManager) parent.getParent()).removeView(parent);
                                unlinkProduct(pos); // unsure if it's a good method signature
                            }
                        });
                        transaction.addView(parent);
                    }
                });
                builder.create().show();
            }
        });
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditOrderActivity.this);
                builder.setTitle("Transaction");

                final EditText sumInput = new EditText(EditOrderActivity.this);
                sumInput.setHint("Sum");
                sumInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                final CalendarView calendar = new CalendarView(EditOrderActivity.this);
                calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        view.setDate(calendar.getTimeInMillis());
                    }
                });
                LinearLayout layout = new LinearLayout(EditOrderActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(sumInput);
                layout.addView(calendar);
                builder.setView(layout);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View view = LayoutInflater.from(EditOrderActivity.this).inflate(R.layout.list_item_transaction, null);
                        final View parent = LayoutInflater.from(EditOrderActivity.this).inflate(R.layout.remove_layout, null);
                        TextView sumText = view.findViewById(R.id.order_trans_sum);
                        sumText.setText(sumInput.getText());
                        TextView dateText = view.findViewById(R.id.order_trans_date);
                        Date date = new Date(calendar.getDate());
                        dateText.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
                        LinearLayout include = parent.findViewById(R.id.elem_include);
                        include.addView(view);
                        final int pos = transaction.getChildCount();
                        ImageView image = parent.findViewById(R.id.elem_delete);
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((ViewManager) parent.getParent()).removeView(parent);
                                removeTransaction(pos);
                            }
                        });
                        transaction.addView(parent);
                        addTransaction(Integer.parseInt(sumInput.getText().toString()), date);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
    }

    private String[] getProductList() {
        return new String[]{"Test", "test2"};
    }

    private Map<String, String> getProduct(int index){
        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        return map;
    }

    private void unlinkProduct(int index) {

    }

    private void addTransaction(int sum, Date date) {

    }

    private void removeTransaction(int index) {

    }

    @Override
    protected void inputData(View v, int index) {

    }

    @Override
    protected void newData(View v) {

    }

    @Override
    protected void addData(View v, int index) {

    }
}
