package com.improve10x.crud.api;

import com.improve10x.crud.Constants;
import com.improve10x.crud.messages.Message;
import com.improve10x.crud.movies.Movie;
import com.improve10x.crud.series.SeriesItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import templates.Template;

public interface CurdService {
    @GET(Constants.MESSAGE_END_POINT)
    Call<List<Message>> fetchMessages();

    @POST(Constants.MESSAGE_END_POINT)
    Call<Message> createMessage(@Body Message message);

    @DELETE(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Template>> fetchTemplates();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Template> createTemplate(@Body Template template);

    @DELETE (Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id")String id);

    @GET(Constants.SERIES_END_POINT)
    Call<List<SeriesItem>> fetchSeriesItems();

    @POST(Constants.SERIES_END_POINT)
    Call<SeriesItem> createSeriesItem(@Body SeriesItem series);

    @DELETE(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> deleteSeriesItem(@Path("id")String id);

    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    Call<Movie> createMovies(@Body Movie movie);
}
