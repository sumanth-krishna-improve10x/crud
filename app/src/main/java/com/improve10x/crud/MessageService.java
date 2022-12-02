package com.improve10x.crud;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessageService {
    @GET("messageHistory")
    Call<List<Message>> fetchData();

    @POST("messageHistory")
    Call<Message> create(@Body Message message);
}
