package com.inkostilaton.dbpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class EditEmpActivity extends EditActivity {

    private EditText name;
    private EditText title;

    private CalendarView startDate;
    private CalendarView endDate;

    private Spinner superior;
    private Spinner department;
    private Spinner address;

    private ImageButton departmentAdd;
    private ImageButton addressAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = findViewById(R.id.add_emp_name);
        title = findViewById(R.id.add_emp_title);

        startDate = findViewById(R.id.add_emp_start);
        endDate = findViewById(R.id.add_emp_end);

        superior = findViewById(R.id.add_emp_superior_select);
        department = findViewById(R.id.add_emp_department_select);
        address = findViewById(R.id.add_emp_office_select);

        departmentAdd = findViewById(R.id.add_emp_department_new);
        addressAdd = findViewById(R.id.add_emp_office_new);

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
        departmentAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditEmpActivity.this);
                builder.setTitle("Department");
                final EditText input = new EditText(EditEmpActivity.this);
                input.setHint("Name");
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addDepartment(input.getText().toString());
                        ((BaseAdapter) department.getAdapter()).notifyDataSetChanged();
                        int count = department.getAdapter().getCount();
                        if (count > 0) {
                            department.setSelection(count - 1);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
        addressAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditEmpActivity.this);
                builder.setTitle("Office");
                final EditText input = new EditText(EditEmpActivity.this);
                input.setHint("Address");
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addAddress(input.getText().toString());
                        ((BaseAdapter) address.getAdapter()).notifyDataSetChanged();
                        int count = address.getAdapter().getCount();
                        if (count > 0) {
                            address.setSelection(count - 1);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
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

    private void addDepartment(String name) {

    }

    private void addAddress(String address) {

    }
}
