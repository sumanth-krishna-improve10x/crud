package com.improve10x.crud.series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeriesService {
    @GET("series")
    Call<List<Series>> fetchData();

    @POST("series")
    Call<Series> create(@Body Series series);

    @DELETE("series{/id}")
    Call<Void> deleteMessage (@Path("id")String id);

}
