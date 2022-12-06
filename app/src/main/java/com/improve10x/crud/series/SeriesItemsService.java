package com.improve10x.crud.series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeriesItemsService {
    @GET("sumanthSeries")
    Call<List<SeriesItem>> fetchSeriesItems();

    @POST("sumanthSeries")
    Call<SeriesItem> createSeriesItem(@Body SeriesItem series);

    @DELETE("sumanthSeries{/id}")
    Call<Void> deleteSeriesItem(@Path("id")String id);

}
