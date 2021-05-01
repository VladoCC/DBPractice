package com.inkostilaton.dbpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.inkostilaton.dbpractice.MainActivity.database;

public class EditProductActivity extends EditActivity {

    private EditText name;

    private CalendarView startDate;
    private CalendarView endDate;

    private Spinner type;
    private ImageButton typeAdd;

    ArrayAdapter<CharSequence> typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = findViewById(R.id.add_prod_name);

        startDate = findViewById(R.id.add_prod_start);
        endDate = findViewById(R.id.add_prod_end);

        type = findViewById(R.id.add_prod_type_select);
        typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, database.getTypes());
        type.setAdapter(typeAdapter);

        typeAdd = findViewById(R.id.add_prod_type);

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
        typeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProductActivity.this);
                builder.setTitle("Type");
                final EditText input = new EditText(EditProductActivity.this);
                input.setHint("Name");
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addType(input.getText().toString());
                        typeAdapter.add(input.getText().toString());
                        ((BaseAdapter) type.getAdapter()).notifyDataSetChanged();
                        int count = type.getAdapter().getCount();
                        if (count > 0) {
                            type.setSelection(count - 1);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
    }

    @Override
    protected void updateData(View v, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedStartDate = sdf.format(new Date(startDate.getDate()));
        String selectedEndDate = sdf.format(new Date(endDate.getDate()));
        ProductModel product= new ProductModel(index, name.getText().toString(), type.getSelectedItem().toString(), selectedStartDate, selectedEndDate);
        database.updateProduct(product);
    }

    private void addType(String name) {
        ProductTypeModel productType = new ProductTypeModel(name);
        database.addProductType(productType);
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
        ProductModel product= new ProductModel(index, name.getText().toString(), type.getSelectedItem().toString(), selectedStartDate, selectedEndDate);
        database.addProduct(product);
    }
}
