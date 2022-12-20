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

public class BaseAddEditActivity extends BaseActivity {
    protected CurdService curdService;
    protected EditText quoteTxt;
    protected EditText authorTxt;
    protected EditText categoryTxt;
    protected EditText quoteImageImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quotes);
        setupViews();
        setupApiService();
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    private void setupViews() {
        quoteTxt = findViewById(R.id.quote_txt);
        authorTxt = findViewById(R.id.authorname_txt);
        categoryTxt = findViewById(R.id.category_txt);
        quoteImageImg = findViewById(R.id.quote_image_img);
    }

    protected  Quote createQuotes(String quote, String author, String category, String imgUrl) {
        Quote quotes = new Quote();
        quotes.quotes = quote;
        quotes.authorName = author;
        quotes.category = category;
        quotes.imageUrl = imgUrl;
        return quotes;
    }
}