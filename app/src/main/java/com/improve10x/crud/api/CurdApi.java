package com.improve10x.crud.api;

import com.improve10x.crud.Constants;
import com.improve10x.crud.api.CurdService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurdApi {

    public CurdService createCurdService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CurdService curdService = retrofit.create(CurdService.class);
        return curdService;
    }
}
