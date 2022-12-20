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

public class BaseAddEditSeriesItemActivity extends BaseActivity {
    protected CurdService curdService;
    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
    protected EditText seriesImgUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        setupViews();
        setupApiService();
    }

    private void setupViews() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImgUrlTxt = findViewById(R.id.series_imgurl_txt);
    }

    private void setupApiService() {
        CurdApi crudApi = new CurdApi();
        curdService = crudApi.createCurdService();
    }


    protected SeriesItem createSeriesItem(String id, String name, String imgUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = id;
        series.title = name;
        series.imageUrl = imgUrl;
        return series;
    }
}
