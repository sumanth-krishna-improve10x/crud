package com.improve10x.crud.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {
    public CurdService curdService;
    public ArrayList<Movie> movies;
    public RecyclerView moviesRv;
    public MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        getSupportActionBar().setTitle("Movie");
        setupViews();
        handleAdd();
        setMovies();
        setupAdapter();
        setupApiService();
    }

    private void setupApiService() {
        CurdApi api = new CurdApi();
        curdService = api.createCurdService();
    }

    private void setupAdapter() {
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movies);
        moviesRv.setAdapter(moviesAdapter);

    }

    public void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addMovieIntent = new Intent(this,AddMovieActivity.class);
            startActivity(addMovieIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMovies();
    }

    public void fetchMovies(){
        Call<List<Movie>> call = curdService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });

    }

    public void setupViews() {
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this,2));
        moviesRv.setAdapter(moviesAdapter);


    }

    public void setMovies(){
        movies = new ArrayList<>();
    }
}