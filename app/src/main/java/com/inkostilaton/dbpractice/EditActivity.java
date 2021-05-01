package com.inkostilaton.dbpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ornach.nobobutton.NoboButton;

public abstract class EditActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        int resId = getIntent().getIntExtra("layout", R.layout.activity_main);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(resId, null);

        LinearLayout layout = findViewById(R.id.add_body);

        final int index = getIntent().getIntExtra("index", -1);
        if (index == -1) {
            newData(view);
        } else {
            inputData(view, index);
        }

        layout.addView(view);

        NoboButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > -1) {
                    updateData(view, index);
                    setResult(RESULT_OK);
                    finish();
                }
                else {
                    addData(view, index);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        if (index > -1) {
            addButton.setText("Edit");
        }
    }

    protected abstract void updateData(View v, int index);

    protected abstract void inputData(View v, int index);

    protected abstract void newData(View v);

    protected abstract void addData(View v, int index);
}
