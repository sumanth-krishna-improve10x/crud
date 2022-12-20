package com.improve10x.crud.messages;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {
    private CurdService curdService;
    private ArrayList<Message> messageList;
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        log("onCreate");
        getSupportActionBar().setTitle("Message");
        setupApiService();
        handleAdd();
        setupData();
        setupMessagesRv();
        setupAdapter();
    }

    private void setupAdapter() {
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messageList);
        messagesRv.setAdapter(messagesAdapter);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Message message) {
                Intent intent = new Intent(MessagesActivity.this, EditMessageActivity.class);
                intent.putExtra(Constants.KEY_MESSAGE,message);
                startActivity(intent);
                //showToast("onItemClick");
            }

            @Override
            public void onItemDelete(Message message) {
                showToast("onItemDelete");
                deleteMessage(message);
            }

            @Override
            public void onItemEdit(Message message) {
                showToast("onItemEdit");
            }
        });
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    private void deleteMessage(Message message) {
        Call<Void> call = curdService.deleteMessage(message.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMessages();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addMessageIntent = new Intent(this, AddMessagesActivity.class);
            startActivity(addMessageIntent);
        });
    }

    private void fetchMessages() {
        Call<List<Message>> call = curdService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });
    }

    private void setupMessagesRv() {
        messagesRv = findViewById(R.id.message_rcv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupData() {
        messageList = new ArrayList<>();
    }
}