package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseActivity {
    private CurdService curdService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        getSupportActionBar().setTitle("AddSeries");
        setupApiService();
       handleAdd();
    }

    private void setupApiService() {
        CurdApi crudApi = new CurdApi();
        curdService = crudApi.createCurdService();
    }

    public void handleAdd() {
            Button addBtn = findViewById(R.id.add_btn);
            addBtn.setOnClickListener(view -> {
                EditText seriesIdTxt = findViewById(R.id.series_id_txt);
                String id = seriesIdTxt.getText().toString();
                EditText seriesNameTxt = findViewById(R.id.series_name_txt);
                String name = seriesNameTxt.getText().toString();
                EditText seriesImgUrlTxt = findViewById(R.id.series_imgurl_txt);
                String imgUrl = seriesImgUrlTxt.getText().toString();
                createSeriesItem(id, name, imgUrl);
            });
    }

    private void createSeriesItem(String id, String name, String imgUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = id;
        series.title = name;
        series.imageUrl = imgUrl;

        Call<SeriesItem> call = curdService.createSeriesItem(series);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });
    }
}