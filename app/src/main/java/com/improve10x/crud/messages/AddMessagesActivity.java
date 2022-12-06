package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessagesActivity extends AppCompatActivity {

    public static class AddMessageActivity extends AppCompatActivity {
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_message);
            getSupportActionBar().setTitle("Add Message");
            handleAdd();
        }
    
        public void handleAdd(){
            Button addBtn = findViewById(R.id.add_btn);
            addBtn.setOnClickListener(view -> {
                EditText nameTxt = findViewById(R.id.name_txt);
                String name = nameTxt.getText().toString();
                EditText phoneTxt = findViewById(R.id.phone_txt);
                String phone = phoneTxt.getText().toString();
                EditText messageTxt = findViewById(R.id.messages_txt);
                String message = messageTxt.getText().toString();
                createMessage(name, phone, message);
    
            });
        }
    
        public void createMessage(String name, String phone, String message) {
            Message messages = new Message();
            messages.name = name;
            messages.phoneNumber = phone;
            messages.messagesText = message;
            MessagesApi messageApi = new MessagesApi();
            MessagesService messageService = messageApi.createMessagesService();
           Call<Message> call =  messageService.createMessage(messages);
           call.enqueue(new Callback<Message>() {
               @Override
               public void onResponse(Call<Message> call, Response<Message> response) {
                   Toast.makeText(AddMessageActivity.this, "Successfully loaded", Toast.LENGTH_SHORT).show();
                   finish();
               }
    
               @Override
               public void onFailure(Call<Message> call, Throwable t) {
                   Toast.makeText(AddMessageActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
               }
           });
    
        }
    }
}