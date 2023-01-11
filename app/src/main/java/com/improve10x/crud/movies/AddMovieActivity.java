package com.improve10x.crud.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends AppCompatActivity {
    private CurdService curdService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setTitle("AddMovie");
        handleAdd();
        setupApiService();
    }

    private void setupApiService() {
        CurdApi api = new CurdApi();
        curdService = api.createCurdService();
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
            EditText descriptionTxt = findViewById(R.id.descripition_txt);
            String descriptionTextTxt = descriptionTxt.getText().toString();
            createMovies(movieId,movieName,series,movieImg,descriptionTextTxt);

        });
    }

    private void createMovies(String movieId, String movieName, String series, String movieImg, String descriptionTextTxt) {
        Movie movieList = new Movie();
        movieList.id = movieId;
        movieList.name = movieName;
        movieList.series = series;
        movieList.imageUrl = movieImg;
        movieList.description = descriptionTextTxt;

        Call<Movie> call = curdService.createMovies(movieList);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Toast.makeText(AddMovieActivity.this, "Successfully done", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddMovieActivity.this, "Failed to get load", Toast.LENGTH_SHORT).show();

            }
        });
    }
}