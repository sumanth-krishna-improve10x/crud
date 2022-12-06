package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends AppCompatActivity {
    public ArrayList<Series> series;
    public RecyclerView seriesRcv;
    public  SeriesAdapter seriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        handleBtn();
        setUpData();
        setRecyclerview();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchData();
    }

    public void deleteMessage(Series series){
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.deleteMessage(series.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesListActivity.this, "Sucessfully done", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public void handleBtn() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addSeriesIntent = new Intent(this,AddSeriesActivity.class);
            startActivity(addSeriesIntent);
        });
    }

    public void fetchData(){
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchData();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                seriesAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }

    public void setRecyclerview(){
        seriesRcv = findViewById(R.id.series_rcv);
        seriesRcv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesRcv.setAdapter(seriesAdapter);
        seriesAdapter.setData(series);
        seriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Series series) {
                Toast.makeText(SeriesListActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Series series) {
                Toast.makeText(SeriesListActivity.this, "onItemDelete", Toast.LENGTH_SHORT).show();
                deleteMessage(series);

            }

            @Override
            public void onItemEdit(Series series) {
                Toast.makeText(SeriesListActivity.this, "onItemEdit", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setUpData(){
        series = new ArrayList<>();
    }
}