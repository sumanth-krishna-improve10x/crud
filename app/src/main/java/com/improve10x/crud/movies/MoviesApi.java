package com.improve10x.crud.movies;

import com.improve10x.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApi {

    public MoviesService createMoviesService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesService moviesService = retrofit.create(MoviesService.class);
        return moviesService;
    }
}
