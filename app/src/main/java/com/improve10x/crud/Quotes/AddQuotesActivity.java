package com.improve10x.crud.Quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10x.crud.R;

public class AddQuotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quotes);
        getSupportActionBar().setTitle("Add Quotes");
    }
}