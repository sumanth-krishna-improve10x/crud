package com.improve10x.crud.Quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;

public class QuotesActivity extends BaseActivity {
   private ArrayList<Quotes> quotes;
   private RecyclerView quotesRv;
   private Button addBtn;
   private QuotesAdapter quotesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupViews();
        setupAdapter();
        setData();
    }

    private void setupAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesRv.setAdapter(quotesAdapter);
        quotesAdapter.setData(quotes);
    }


    private void setupViews() {
        quotesRv = findViewById(R.id.quotes_rv);
        addBtn = findViewById(R.id.add_btn);
        quotesRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData() {
        quotes = new ArrayList<>();
    }
}