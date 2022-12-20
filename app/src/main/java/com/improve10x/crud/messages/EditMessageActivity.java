package com.improve10x.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity {
    private Message message;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
            handleEdit();
            handleEditBtn();
        }
    }

    private void setupViews(){
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showData() {
        nameTxt.setText(message.name);
        phoneTxt.setText(message.phoneNumber);
        messageText.setText(message.messagesText);
    }

    private void handleEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneTxt.getText().toString();
            String messageTxt = messageText.getText().toString();
            Message updatedMessage = createMessage(name, phoneNumber, messageTxt);
            updateMessage(message.id, updatedMessage);
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
}
