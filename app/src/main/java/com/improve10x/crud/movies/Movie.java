package com.improve10x.crud.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("_id")
    public String id;
    @SerializedName("description")
    public String description;
    @SerializedName("name")
    public String name;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("seriesId")
    public String series;
}
