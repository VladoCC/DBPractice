package com.inkostilaton.dbpractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        toolbar = findViewById(R.id.data_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        recyclerView = findViewById(R.id.data_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        type = getIntent().getIntExtra("type", 1);
        adapter = createAdapter(type);
        recyclerView.setAdapter(adapter);
    }

    private DataAdapter createAdapter(int type) {
        if (type == 1) {
            return new OrderAdapter(this, recyclerView);
        } else if (type == 2) {
            return new CustomAdapter(this, recyclerView);
        } else if (type == 3) {
            return new EmpAdapter(this, recyclerView);
        } else if (type == 4) {
            return new ProductAdapter(this, recyclerView);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.setSearchQuery(newText.toLowerCase());
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.setSearchQuery();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            openAdd();
        }

        return super.onOptionsItemSelected(item);
    }

    public int getResId() {
        if (type == 2) {
            return R.layout.add_custom;
        }else if (type == 3) {
            return R.layout.add_emp;
        } else if (type == 4) {
            return R.layout.add_product;
        }
        return type;//todo change return
    }

    public void openAdd() {
        Intent intent = new Intent(this, EditCustomActivity.class);
        intent.putExtra("layout", getResId());
        startActivityForResult(intent, 0);
    }

    public void openAdd(int index) {
        Intent intent = new Intent(this, EditCustomActivity.class);
        intent.putExtra("layout", getResId());
        intent.putExtra("index", index);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }
}
