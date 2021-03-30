package com.inkostilaton.dbpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class EditProductActivity extends EditActivity {

    private EditText name;
    private EditText startDate;
    private EditText endDate;

    private Spinner type;
    private ImageButton typeAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = findViewById(R.id.add_prod_name);
        startDate = findViewById(R.id.add_prod_start);
        endDate = findViewById(R.id.add_prod_end);

        type = findViewById(R.id.add_emp_office_select);
        typeAdd = findViewById(R.id.add_emp_department_new);

        typeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProductActivity.this);
                builder.setTitle("Department");
                final EditText input = new EditText(EditProductActivity.this);
                input.setHint("Name");
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addType(input.getText().toString());
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

    private void addType(String name) {

    }

    @Override
    protected void inputData(View v, int index) {

    }

    @Override
    protected void addData(View v, int index) {

    }
}
