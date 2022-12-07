package com.improve10x.crud.movies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView movieImgImg;
    public TextView titleTxt;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImgImg = itemView.findViewById(R.id.movieimg_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
