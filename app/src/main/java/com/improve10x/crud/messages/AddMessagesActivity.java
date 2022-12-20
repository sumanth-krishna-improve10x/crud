package com.improve10x.crud.messages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessagesActivity  extends BaseAddEditMessageActivity {

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
        setupViews();
        handleBtn();
        handleAdd();
    }

    private void setupViews(){
        addBtn = findViewById(R.id.add_btn);
    }
    private void handleBtn(){
        addBtn.setVisibility(View.VISIBLE);
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
}
