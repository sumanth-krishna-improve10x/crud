package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.CurdApi;
import com.improve10x.crud.CurdService;
import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends AppCompatActivity {
    private ArrayList<SeriesItem> seriesList;
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        handleAdd();
        setupData();
        setupSeriesItemsRv();
    }


    @Override
    protected void onResume() {
        super.onResume();
        fetchSeriesItems();
    }

    private void deleteSeriesItem(SeriesItem series){
        CurdApi curdApi = new CurdApi();
        CurdService curdService = curdApi.createCurdService();
        Call<Void> call = curdService.deleteSeriesItem(series.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesItemsActivity.this, "Successfully done", Toast.LENGTH_SHORT).show();
                fetchSeriesItems();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesItemsActivity.this, "Failed to get load", Toast.LENGTH_SHORT).show();

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
        CurdApi curdApi = new CurdApi();
        CurdService curdService = curdApi.createCurdService();
        Call<List<SeriesItem>> call = curdService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                List<SeriesItem> seriesItems = response.body();
                seriesItemsAdapter.setData(seriesItems);
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {

            }
        });
    }

    public void setupSeriesItemsRv(){
        seriesItemsRv = findViewById(R.id.series_rcv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsRv.setAdapter(seriesItemsAdapter);
        seriesItemsAdapter.setData(seriesList);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(SeriesItem series) {
                Toast.makeText(SeriesItemsActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(SeriesItem series) {
                Toast.makeText(SeriesItemsActivity.this, "onItemDelete", Toast.LENGTH_SHORT).show();
                deleteSeriesItem(series);

            }

            @Override
            public void onItemEdit(SeriesItem series) {
                Toast.makeText(SeriesItemsActivity.this, "onItemEdit", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupData(){
        seriesList = new ArrayList<>();
    }
}