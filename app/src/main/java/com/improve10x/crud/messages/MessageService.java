package com.improve10x.crud.messages;


import com.improve10x.crud.messages.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageService {
    @GET("messageHistory")
    Call<List<Message>> fetchData();

    @POST("messageHistory")
    Call<Message> create(@Body Message message);

    @DELETE("messageHistory/{id}")
    Call<Void> deleteMessage(@Path("id") String id);
}
