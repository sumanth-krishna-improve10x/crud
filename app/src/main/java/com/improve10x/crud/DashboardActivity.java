package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");

        ImageButton messageImgBtn = findViewById(R.id.message_imgbtn);
        messageImgBtn.setOnClickListener(view -> {
            Intent messageIntent = new Intent(this,MessageActivity.class);
            startActivity(messageIntent);
        });
    }
}