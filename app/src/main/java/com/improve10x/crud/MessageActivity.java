package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity<onPostResume> extends AppCompatActivity {

    public ArrayList<Message> messageList;
    public RecyclerView messageRcv;
    public MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().setTitle("Message");
        handleButton();
        setData();
        setRecyclerView();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchData();
    }

    public void handleButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addMessageIntent = new Intent(this,AddMessageActivity.class);
            startActivity(addMessageIntent);
        });
    }


    public void fetchData(){
        MessageApi messageApi = new MessageApi();
        MessageService messageService = messageApi.createMessageService();
        Call<List<Message>> call = messageService.fetchData();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<com.improve10x.crud.Message> messages = response.body();
                messageAdapter.setData(messages);
            }


            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MessageActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setRecyclerView() {
        messageRcv = findViewById(R.id.message_rcv);
        messageRcv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new  MessageAdapter();
        messageAdapter.setData(messageList);
        messageRcv.setAdapter(messageAdapter);
    }

    public void setData() {
        messageList = new ArrayList<>();
    }
}