package com.andriod.asifnewaz.oceanologicaldictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BookmarksActivity extends AppCompatActivity {

    DatabaseOperations databaseOperations;
    RecyclerView my_recycler_view;
    ArrayList<Model> bookmarksList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        databaseOperations = new DatabaseOperations(this);
        my_recycler_view = findViewById(R.id.my_recycler_view);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        my_recycler_view.setHasFixedSize(true);
        bookmarksList = databaseOperations.getAllModelValues();
        customAdapter = new CustomAdapter(bookmarksList,BookmarksActivity.this);
        my_recycler_view.setAdapter(customAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
