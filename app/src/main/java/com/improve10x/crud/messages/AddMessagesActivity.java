package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessagesActivity extends AppCompatActivity {


    public static class AddMessageActivity extends BaseActivity {
        public CurdService curdService;
        private Button addBtn;
        private EditText nameTxt;
        private EditText phoneTxt;
        private EditText messageText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_message);
            getSupportActionBar().setTitle("Add Message");
            setupViews();
            setupApiService();
            handleAdd();
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

        private static Message createMessage(String name, String phone, String message) {
            Message messages = new Message();
            messages.name = name;
            messages.phoneNumber = phone;
            messages.messagesText = message;
            return messages;
    }
}