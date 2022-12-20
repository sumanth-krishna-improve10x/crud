package com.improve10x.crud.messages;

import android.os.Bundle;
import android.widget.EditText;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.base.BaseActivity;

public class BaseAddEditMessageActivity extends BaseActivity {
    protected CurdService curdService;
    protected EditText nameTxt;
    protected EditText phoneTxt;
    protected EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        setupViews();
        setupApiService();
    }

    protected Message createMessage(String name, String phone, String message) {
        Message messages = new Message();
        messages.name = name;
        messages.phoneNumber = phone;
        messages.messagesText = message;
        return messages;
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    private void setupViews() {
        nameTxt = findViewById(R.id.name_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        messageText = findViewById(R.id.messages_text);
    }
}
