package com.improve10x.crud.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public List<Movie> movies;

    public void setData(List<Movie> moviesList){
        movies = moviesList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item,parent,false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.titleTxt.setText(movie.description);
        Picasso.get().load(movie.imageUrl).into(holder.movieImgImg);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
