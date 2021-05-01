package com.inkostilaton.dbpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    ArrayAdapter<CharSequence> superiorAdapter;
    ArrayAdapter<CharSequence> departmentAdapter;
    ArrayAdapter<CharSequence> officeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        name = findViewById(R.id.add_emp_name);
        title = findViewById(R.id.add_emp_title);

        startDate = findViewById(R.id.add_emp_start);
        endDate = findViewById(R.id.add_emp_end);

        superior = findViewById(R.id.add_emp_superior_select);
        superiorAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, database.getEmployeesNames());
        superior.setAdapter(superiorAdapter);

        department = findViewById(R.id.add_emp_department_select);
        departmentAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, database.getDepartmentsNames());
        department.setAdapter(departmentAdapter);

        address = findViewById(R.id.add_emp_office_select);
        officeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, database.getAddresses());
        address.setAdapter(officeAdapter);

        departmentAdd = findViewById(R.id.add_emp_department_new);
        addressAdd = findViewById(R.id.add_emp_office_new);

        superior.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                        departmentAdapter.add(input.getText().toString());
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
                        officeAdapter.add(input.getText().toString());
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
    protected void updateData(View v, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedStartDate = sdf.format(new Date(startDate.getDate()));
        String selectedEndDate = sdf.format(new Date(endDate.getDate()));
        EmpModel employee = new EmpModel(index, name.getText().toString(), title.getText().toString(), department.getSelectedItem().toString(), address.getSelectedItem().toString(), superior.getSelectedItem().toString(), selectedStartDate, selectedEndDate);
        database.updateEmployee(employee);
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
        EmpModel employee = new EmpModel(index, name.getText().toString(), title.getText().toString(), department.getSelectedItem().toString(), address.getSelectedItem().toString(), superior.getSelectedItem().toString(), selectedStartDate, selectedEndDate);
        database.addEmployee(employee);
    }

    private void addDepartment(String name) {
        DepartmentModel department = new DepartmentModel(name);
        database.addDepartment(department);
    }

    private void addAddress(String address) {
        OfficeModel office = new OfficeModel(address);
        database.addOffice(office);
    }

}
