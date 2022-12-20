package com.improve10x.crud.Quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuoteActivity extends BaseAddEditActivity{
    private  Quote quote;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_QUOTES)) {
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTES);
            showData();
            showEdit();
            handleEdit();
        }
    }

    private void setupView() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showEdit() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showData() {
        quoteTxt.setText(quote.quotes);
        authorTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        quoteImageImg.setText(quote.imageUrl);
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
}


