package com.inkostilaton.dbpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditCustomActivity extends EditActivity {

    private EditText name;
    private EditText postal;
    private EditText state;
    private EditText city;
    private EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = findViewById(R.id.add_custom_name);
        postal = findViewById(R.id.add_custom_postal);
        state = findViewById(R.id.add_custom_state);
        city = findViewById(R.id.add_custom_city);
        address = findViewById(R.id.add_custom_address);
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
