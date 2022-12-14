package com.improve10x.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseActivity {
    public CurdService curdService;
    private Button addBtn;
    private EditText nameTxt;
    private EditText phoneTxt;
    private EditText messageText;
    private Message message;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MESSAGE)){
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
            handleEdit();
            handleEditBtn();
        } else {
            getSupportActionBar().setTitle("Add Message");
            handleBtn();
            handleAdd();
        }
    }

    private void handleEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleBtn(){
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneTxt.getText().toString();
            String messageTxt = messageText.getText().toString();
            Message updatedMessage = createMessage(name, phoneNumber,messageTxt);
            updateMessage(message.id,updatedMessage);
        });

    }

    private void updateMessage(String id, Message updatedMessage) {
        Call<Void> call = curdService.updateMessage(id,updatedMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get Message");
            }
        });
    }

    private void showData() {
        nameTxt.setText(message.name);
        phoneTxt.setText(message.phoneNumber);
        messageText.setText(message.messagesText);
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }


    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        nameTxt = findViewById(R.id.name_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        messageText = findViewById(R.id.messages_text);
        editBtn = findViewById(R.id.edit_btn);
    }

    private void handleAdd(){
        addBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phone = phoneTxt.getText().toString();
            String messageTxt = messageText.getText().toString();
            Message message = createMessage(name, phone, messageTxt);
            saveMessage(message);
        });
    }

    private void saveMessage(Message message) {
        Call<Message> call = curdService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("Something went wrong");
            }
        });
    }

    private Message createMessage(String name, String phone, String message) {
        Message messages = new Message();
        messages.name = name;
        messages.phoneNumber = phone;
        messages.messagesText = message;
        return messages;
    }
}
