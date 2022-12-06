package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {

    public ArrayList<Message> messageList;
    public RecyclerView messagesRv;
    public MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().setTitle("Message");
        handleAdd();
        setupData();
        setupMessagesRv();
    }

    private void deleteMessage(Message message) {
        MessagesApi api = new MessagesApi();
        MessagesService messageService = api.createMessagesService();
        Call<Void> call = messageService.deleteMessage(message.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MessagesActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMessages();
    }

    public void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addMessageIntent = new Intent(this, AddMessagesActivity.AddMessageActivity.class);
            startActivity(addMessageIntent);
        });
    }

    public void fetchMessages(){
        MessagesApi messageApi = new MessagesApi();
        MessagesService messageService = messageApi.createMessagesService();
        Call<List<Message>> call = messageService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
            }


            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupMessagesRv() {
        messagesRv = findViewById(R.id.message_rcv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messageList);
        messagesRv.setAdapter(messagesAdapter);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Message message) {
                Toast.makeText(MessagesActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Message message) {
                Toast.makeText(MessagesActivity.this, "onItemDeleted", Toast.LENGTH_SHORT).show();
                deleteMessage(message);

            }

            @Override
            public void onItemEdit(Message message) {
                Toast.makeText(MessagesActivity.this, "onItemClicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupData() {

        messageList = new ArrayList<>();
    }
}