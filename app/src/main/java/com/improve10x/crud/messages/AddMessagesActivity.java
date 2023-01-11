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
        handleBtn();
        handleAdd();
    }

    private void handleBtn(){
        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAdd(){
        binding.addBtn.setOnClickListener(view -> {
            String name = binding.nameTxt.getText().toString();
            String phone = binding.phoneTxt.getText().toString();
            String messageTxt = binding.messagesText.getText().toString();
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
