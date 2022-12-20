package com.improve10x.crud.series;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import templates.BaseAddEditTemplatesActivity;

public class AddSeriesActivity extends BaseAddEditSeriesItemActivity{
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add series");
        setupView();
        showAddBtn();
        handleAdd();
    }

    private void setupView() {
        addBtn = findViewById(R.id.add_btn);
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
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
}
