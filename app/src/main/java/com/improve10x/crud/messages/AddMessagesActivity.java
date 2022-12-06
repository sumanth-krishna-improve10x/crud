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
            handleButton();
        }
    
        public void handleButton(){
            Button addBtn = findViewById(R.id.add_btn);
            addBtn.setOnClickListener(view -> {
                EditText nameTxt = findViewById(R.id.name_txt);
                String name = nameTxt.getText().toString();
                EditText phoneTxt = findViewById(R.id.phone_txt);
                String phone = phoneTxt.getText().toString();
                EditText messageTxt = findViewById(R.id.messages_txt);
                String message = messageTxt.getText().toString();
                createData(name, phone, message);
    
            });
        }
    
        public void createData(String name, String phone, String message) {
            Message message1 = new Message();
            message1.name = name;
            message1.phoneNumber = phone;
            message1.messagesText = message;
            MessagesApi messageApi = new MessagesApi();
            MessagesService messageService = messageApi.createMessageService();
           Call<Message> call =  messageService.create(message1);
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