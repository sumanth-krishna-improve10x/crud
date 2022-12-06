package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        getSupportActionBar().setTitle("AddSeries");
       handleAdd();
    }

        public void handleAdd() {
            Button addBtn = findViewById(R.id.add_btn);
            addBtn.setOnClickListener(view -> {
                EditText seriesId = findViewById(R.id.series_id);
                String id = seriesId.getText().toString();
                EditText seriesName = findViewById(R.id.series_name);
                String name = seriesName.getText().toString();
                EditText seriesImgUrl = findViewById(R.id.series_imgurl);
                String imgUrl = seriesImgUrl.getText().toString();
                createSeries(id, name, imgUrl);
            });
    }

    private void createSeries(String id, String name, String imgUrl) {
        Series series = new Series();
        series.id = id;
        series.title = name;
        series.imageUrl = imgUrl;

        SeriesApi api = new SeriesApi();
        SeriesService seriesService = api.createSeriesService();
        Call<Series> call = seriesService.create(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddSeriesActivity.this, "Successfully loaded", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddSeriesActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }
}