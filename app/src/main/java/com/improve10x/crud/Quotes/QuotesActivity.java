package com.improve10x.crud.Quotes;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends BaseActivity {
    private CurdService crudService;
   private ArrayList<Quote> quotes;
   private RecyclerView quotesRv;
   private Button addBtn;
   private QuotesAdapter quotesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupViews();
        setupApiService();
        handleAdd();
        setData();
        setupAdapter();
        fetchQuotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchQuotes();
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddQuoteActivity.class);
            startActivity(intent);
        });
    }

    private void fetchQuotes() {
        Call<List<Quote>> call = crudService.fetchQuotes();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> quotes = response.body();
                quotesAdapter.setData(quotes);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                showToast("Failed to get loaded");

            }
        });
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        crudService = curdApi.createCurdService();
    }

    private void setupAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesRv.setAdapter(quotesAdapter);
        quotesAdapter.setData(quotes);
        quotesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Quote quote) {
                Intent intent = new Intent(QuotesActivity.this, EditQuoteActivity.class);
                intent.putExtra(Constants.KEY_QUOTES,quote);
                startActivity(intent);
              // showToast("onItemClicked");
            }

            @Override
            public void onItemDelete(Quote quote) {
                showToast("onItemDelete");
                deleteQuotes(quote);
            }

            @Override
            public void onItemEdit(Quote quote) {
                showToast("onItemEdit");

            }
        });
    }

    private void deleteQuotes(Quote quote) {
        Call<Void> call = crudService.deleteQuotes(quote.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                fetchQuotes();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get loaded");

            }
        });
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