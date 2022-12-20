package com.improve10x.crud.Quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends BaseAddEditActivity{
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Quote");
        setupView();
        showAdd();
        handleAdd();
    }

    private void setupView(){
        addBtn = findViewById(R.id.add_btn);
    }

    private void showAdd() {
        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String quotes = quoteTxt.getText().toString();
            String author = authorTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imgUrl = quoteImageImg.getText().toString();
            Quote quote = createQuotes(quotes,author,category,imgUrl);
            saveQuote(quote);
        });
    }

    private void saveQuote(Quote quote){
        Call<Quote> call = curdService.createQuotes(quote);
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                showToast("Filed to get load");
            }
        });
    }
}
