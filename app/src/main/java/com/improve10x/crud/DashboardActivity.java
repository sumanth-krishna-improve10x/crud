package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.improve10x.crud.Quotes.QuotesActivity;
import com.improve10x.crud.messages.MessagesActivity;
import com.improve10x.crud.movies.MoviesActivity;
import com.improve10x.crud.series.SeriesItemsActivity;

import templates.TemplatesActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");

        ImageButton messageImgBtn = findViewById(R.id.message_imgbtn);
        messageImgBtn.setOnClickListener(view -> {
            Intent messageIntent = new Intent(this, MessagesActivity.class);
            startActivity(messageIntent);
        });

        ImageButton templateImgBtn = findViewById(R.id.template_imgbtn);
        templateImgBtn.setOnClickListener(view -> {
            Intent templateIntent = new Intent(this, TemplatesActivity.class);
            startActivity(templateIntent);
        });

        ImageButton seriesImgBtn = findViewById(R.id.series_imgbtn);
        seriesImgBtn.setOnClickListener(view -> {
            Intent seriesIntent = new Intent(this, SeriesItemsActivity.class);
            startActivity(seriesIntent);
        });

        ImageButton movieImgBtn = findViewById(R.id.movie_imgbtn);
        movieImgBtn.setOnClickListener(view -> {
            Intent movieIntent = new Intent(this, MoviesActivity.class);
            startActivity(movieIntent);
        });

        ImageButton quotesImgBtn = findViewById(R.id.quotes_img_btn);
        quotesImgBtn.setOnClickListener(view -> {
            Intent quoteIntent = new Intent(this, QuotesActivity.class);
            startActivity(quoteIntent);
        });
    }
}