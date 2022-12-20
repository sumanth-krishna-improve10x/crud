package com.improve10x.crud.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import templates.BaseAddEditTemplatesActivity;

public class EditSeriesActivity extends BaseAddEditSeriesItemActivity {
    private Button editBtn;
    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

    private void setView() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showEditBtn() {
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
}
