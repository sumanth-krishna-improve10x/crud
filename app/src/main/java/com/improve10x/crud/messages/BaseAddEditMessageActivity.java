package com.improve10x.crud.messages;

import android.os.Bundle;
import android.widget.EditText;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityAddMessageBinding;

public class BaseAddEditMessageActivity extends BaseActivity {
    protected CurdService curdService;
    protected ActivityAddMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

}
