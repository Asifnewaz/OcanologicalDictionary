package com.andriod.asifnewaz.oceanologicaldictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView detailText, saveWord;
    TextView wordText;
    Button bookmarkWord;
    Model model;
    int i = 0;
    DatabaseOperations databaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        databaseOperations = new DatabaseOperations(this);

        String word = getIntent().getStringExtra("Word");
        String details = getIntent().getStringExtra("Detail");
        model = (Model) getIntent().getSerializableExtra("Model");

        wordText = findViewById(R.id.wordText);
        detailText = findViewById(R.id.detailsText);
        saveWord = findViewById(R.id.saveWord);
        bookmarkWord = findViewById(R.id.bookmarkWord);

        wordText.setText(model.word);
        detailText.setText(model.definition);
        if (model.isBookmarked == 1) {
            bookmarkWord.setBackgroundResource(R.drawable.bookmark_full);
            saveWord.setText("Bookmarked");
        } else {
            bookmarkWord.setBackgroundResource(R.drawable.bookmark);
            saveWord.setText("Bookmark");
        }

        bookmarkWord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*if (i == 0) {
                    bookmarkWord.setBackgroundResource(R.drawable.bookmark_full);
                    saveWord.setText("Saved");
                    databaseOperations.addEmployee(model);
                    i = 1;
                } else {
                    bookmarkWord.setBackgroundResource(R.drawable.bookmark);
                    databaseOperations.removeSingleModelValue(model);
                    saveWord.setText(" Save  ");
                    i = 0;
                }*/
                if (model.isBookmarked ==1 ) {
                    bookmarkWord.setBackgroundResource(R.drawable.bookmark);
                    saveWord.setText("Bookmark");
                    model.setBookmarked(0);
                    databaseOperations.updateModelValues(model,DatabaseHelper.TABLE_DICTION);
                    databaseOperations.removeSingleModelValue(model);

                } else {
                    bookmarkWord.setBackgroundResource(R.drawable.bookmark_full);
                    saveWord.setText("Bookmarked");
                    model.setBookmarked(1);
                    databaseOperations.updateModelValues(model,DatabaseHelper.TABLE_DICTION);
                    databaseOperations.addEmployee(model);
                }
            }
        });
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
