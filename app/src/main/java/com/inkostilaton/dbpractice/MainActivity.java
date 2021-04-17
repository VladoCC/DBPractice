package com.inkostilaton.dbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.ornach.nobobutton.NoboButton;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private NoboButton orderButton;
    private NoboButton customButton;
    private NoboButton empButton;
    private NoboButton productButton;

    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        orderButton = findViewById(R.id.main_order);
        customButton = findViewById(R.id.main_custom);
        empButton = findViewById(R.id.main_emp);
        productButton = findViewById(R.id.main_product);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openData(1);
            }
        });
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openData(2);
            }
        });
        empButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openData(3);
            }
        });
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openData(4);
            }
        });

        database = new Database(MainActivity.this);
    }

    private void openData(int type) {
        Intent intent = new Intent(this, DataActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
