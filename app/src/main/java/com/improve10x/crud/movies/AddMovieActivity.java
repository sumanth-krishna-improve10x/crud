package com.improve10x.crud.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setTitle("AddMovie");
        handleAdd();
    }

    public void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText movieIdTxt = findViewById(R.id.movie_id_txt);
            String movieId = movieIdTxt.getText().toString();
            EditText movieNameTxt = findViewById(R.id.movie_name_txt);
            String movieName = movieNameTxt.getText().toString();
            EditText seriesTxt  = findViewById(R.id.series_txt);
            String series = seriesTxt.getText().toString();
            EditText movieImgTxt = findViewById(R.id.movie_img_txt);
            String movieImg = movieImgTxt.getText().toString();
            createMovies(movieId,movieName,series,movieImg);
            Spinner descriptionSp = findViewById(R.id.description_sp);
            descriptionSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(AddMovieActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        });
    }

    private void createMovies(String movieId, String movieName, String series,  String movieImg) {
        Movie movieList = new Movie();
        movieList.id = movieId;
        movieList.name = movieName;
        movieList.series = series;
        movieList.imageUrl = movieImg;

        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Movie> call = moviesService.createMovies(movieList);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Toast.makeText(AddMovieActivity.this, "Successfully done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddMovieActivity.this, "Failed to get load", Toast.LENGTH_SHORT).show();

            }
        });


    }
}