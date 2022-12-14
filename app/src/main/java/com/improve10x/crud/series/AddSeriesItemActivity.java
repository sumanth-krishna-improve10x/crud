package com.improve10x.crud.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseActivity {
    private CurdService curdService;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText seriesImgUrlTxt;
    private SeriesItem seriesItem;
    private Button   editBtn;
    private Button addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        } else {
            getSupportActionBar().setTitle("Add series");
            showAddBtn();
            handleAdd();
        }

    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showData() {
        seriesIdTxt.setText(seriesItem.id);
        seriesNameTxt.setText(seriesItem.seriesId);
        seriesImgUrlTxt.setText(seriesItem.imageUrl);

    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String imageUrl = seriesImgUrlTxt.getText().toString();
            SeriesItem updateSeriesItem = createSeriesItem(seriesId, seriesName, imageUrl);
            updatedSeriesItem(seriesItem.id, updateSeriesItem);

        });
    }

    private void updatedSeriesItem(String id, SeriesItem updateSeriesItem) {
        Call<Void> call = curdService.updatedSeriesItem(id, updateSeriesItem);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get loaded");

            }
        });
    }

    private void setupViews() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImgUrlTxt = findViewById(R.id.series_imgurl_txt);
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);

    }

    private void setupApiService() {
        CurdApi crudApi = new CurdApi();
        curdService = crudApi.createCurdService();
    }

    public void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String id = seriesIdTxt.getText().toString();
            String name = seriesNameTxt.getText().toString();
            String imgUrl = seriesImgUrlTxt.getText().toString();
            SeriesItem seriesItem = createSeriesItem(id, name, imgUrl);
            saveSeriesItem(seriesItem);
        });
    }

    private void saveSeriesItem(SeriesItem seriesItem) {
        Call<SeriesItem> call = curdService.createSeriesItem(seriesItem);
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

    private SeriesItem createSeriesItem(String id, String name, String imgUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = id;
        series.title = name;
        series.imageUrl = imgUrl;
        return series;
    }
}
