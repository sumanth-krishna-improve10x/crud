package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;
import com.improve10x.crud.SplashActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

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

    private void deleteMessage(Message message) {
        MessageApi api = new MessageApi();
        MessageService messageService = api.createMessageService();
        Call<Void> call = messageService.deleteMessage(message.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MessageActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MessageActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchData();
    }

    public void handleButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addMessageIntent = new Intent(this, SplashActivity.AddMessageActivity.class);
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
                List<Message> messages = response.body();
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
        messageAdapter.setOnItemActionListener(new onItemActionListener() {
            @Override
            public void onItemClicked(Message message) {
                Toast.makeText(MessageActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Message message) {
                Toast.makeText(MessageActivity.this, "onItemDeleted", Toast.LENGTH_SHORT).show();
                deleteMessage(message);

            }

            @Override
            public void onItemEdit(Message message) {
                Toast.makeText(MessageActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setData() {
        messageList = new ArrayList<>();
    }
}