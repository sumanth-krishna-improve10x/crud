package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {
    private CurdService curdService;
    private ArrayList<SeriesItem> seriesList;
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        log("onCreate");
        setupApiService();
        handleAdd();
        setupData();
        setupSeriesItemsRv();
        setupAdapter();
    }

    private void setupAdapter() {
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsRv.setAdapter(seriesItemsAdapter);
        seriesItemsAdapter.setData(seriesList);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(SeriesItem series) {
                showToast("onItemClicked");
            }

            @Override
            public void onItemDelete(SeriesItem series) {
                showToast("onItemDelete");
                deleteSeriesItem(series);
            }

            @Override
            public void onItemEdit(SeriesItem series) {
                showToast("onItemEdit");
            }
        });
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchSeriesItems();
    }

    private void deleteSeriesItem(SeriesItem series){
        Call<Void> call = curdService.deleteSeriesItem(series.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                fetchSeriesItems();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to gert loaded");
            }
        });
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addSeriesIntent = new Intent(this, AddSeriesItemActivity.class);
            startActivity(addSeriesIntent);
        });
    }

    private void fetchSeriesItems(){
        Call<List<SeriesItem>> call = curdService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                List<SeriesItem> seriesItems = response.body();
                seriesItemsAdapter.setData(seriesItems);
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                showToast("Failed to get load");

            }
        });
    }

    public void setupSeriesItemsRv(){
        seriesItemsRv = findViewById(R.id.series_rcv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupData(){
        seriesList = new ArrayList<>();
    }
}