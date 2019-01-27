package com.jessicathornsby.roomlivedatademo;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import java.util.List;
import android.support.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private ItemViewModel myItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView myRecyclerView = findViewById(R.id.recyclerview);
        final ItemListAdapter myAdapter = new ItemListAdapter(this);
        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        myItemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {

            @Override
            public void onChanged(@Nullable final List<Item> items) {
                myAdapter.setItems(items);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA));
            myItemViewModel.insert(item);

        }

    }

}