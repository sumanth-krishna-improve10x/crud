package com.improve10x.crud.Quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuotesActivity extends BaseActivity {
    private CurdService curdService;
    private EditText quoteTxt;
    private EditText authorTxt;
    private EditText categoryTxt;
    private EditText quoteImageImg;
    private  Quote quote;
    private Button addBtn;
    private Button editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quotes);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_QUOTES)) {
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTES);
            showData();
            handleEdit();
            showEdit();
        } else {
            getSupportActionBar().setTitle("Add Quote");
            showAdd();
            handleAdd();
        }
    }

    private void showAdd() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEdit() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String quoteText = quoteTxt.getText().toString();
            String authorName = authorTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = quoteImageImg.getText().toString();
            Quote updatedQuote = createQuotes(quoteText,authorName,category,imageUrl);
            updateQuote(quote.id , updatedQuote);

        });
    }

    private void updateQuote(String id, Quote updatedQuote) {
        Call<Void> call = curdService.updateQuote(id, updatedQuote);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get load");

            }
        });
    }

    private void showData() {
        quoteTxt.setText(quote.quotes);
        authorTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        quoteImageImg.setText(quote.imageUrl);
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
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

    private Quote createQuotes(String quote, String author, String category, String imgUrl) {
        Quote quotes = new Quote();
        quotes.quotes = quote;
        quotes.authorName = author;
        quotes.category = category;
        quotes.imageUrl = imgUrl;
        return quotes;

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

    private void setupViews() {
        quoteTxt = findViewById(R.id.quote_txt);
        authorTxt = findViewById(R.id.authorname_txt);
        categoryTxt = findViewById(R.id.category_txt);
        quoteImageImg = findViewById(R.id.quote_image_img);
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
    }
}