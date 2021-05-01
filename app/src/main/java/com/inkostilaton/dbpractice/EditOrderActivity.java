package com.inkostilaton.dbpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.inkostilaton.dbpractice.MainActivity.database;

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

    ArrayAdapter<CharSequence> customerAdapter;
    ArrayAdapter<CharSequence> employeeAdapter;

    int ordersNow = database.countRecords() + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        status = findViewById(R.id.add_order_status);
        sum = findViewById(R.id.add_order_sum);

        startDate = findViewById(R.id.add_order_start);
        endDate = findViewById(R.id.add_order_end);

        custom = findViewById(R.id.add_order_custom_select);
        customerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, database.getCustomers());
        custom.setAdapter(customerAdapter);

        emp = findViewById(R.id.add_order_emp_select);
        employeeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, database.getEmployees());
        emp.setAdapter(employeeAdapter);

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
                        String productName = getProduct(i);
                        addProductToList(ordersNow, productName);
                        View view = LayoutInflater.from(EditOrderActivity.this).inflate(R.layout.list_item_product, null);
                        final View parent = LayoutInflater.from(EditOrderActivity.this).inflate(R.layout.remove_layout, null);
                        TextView productText = view.findViewById(R.id.order_prod_name);
                        productText.setText(productName);
                        LinearLayout include = parent.findViewById(R.id.elem_include);
                        include.addView(view);
                        final int pos = product.getChildCount();
                        ImageView image = parent.findViewById(R.id.elem_delete);
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((ViewManager) parent.getParent()).removeView(parent);
                                unlinkProduct(pos);
                            }
                        });
                        product.addView(parent);
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
                sumInput.setHint("Value");
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
                        addTransaction(Integer.parseInt(sumInput.getText().toString()), date, ordersNow);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
    }

    private String[] getProductList() {
        List<String> productList = database.getProducts();
        int sizeOfArray = productList.size();
        String[] productArray = new String[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            productArray[i] = productList.get(i);
        }
        return productArray;
    }

    private String getProduct(int index){
        return getProductList()[index];
    }

    private void unlinkProduct(int index) {

    }

    private void addProductToList(int id, String name) {
        ProductListModel productListModel = new ProductListModel(id, name);
        database.addProductList(productListModel);
    }

    private void addTransaction(int sum, Date date, int order_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = sdf.format(date);
        TransactionModel transaction = new TransactionModel(selectedDate, sum, order_id);
        database.addTransaction(transaction);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedStartDate = sdf.format(new Date(startDate.getDate()));
        String selectedEndDate = sdf.format(new Date(endDate.getDate()));
        OrderModel order = new OrderModel(index, custom.getSelectedItem().toString(), status.getText().toString(), emp.getSelectedItem().toString(), selectedStartDate, selectedEndDate, sum.getText().toString(), database.getProductListOfOrder(ordersNow), database.getTransactionsOfOrder(ordersNow));
        database.addOrder(order);
    }
}
