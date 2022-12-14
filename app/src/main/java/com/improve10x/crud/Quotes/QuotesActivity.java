package com.improve10x.crud.Quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10x.crud.R;

import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {
    public  ArrayList<Quotes> quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");

    }
}