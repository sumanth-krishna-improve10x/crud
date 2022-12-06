package com.improve10x.crud.series;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Target;

public class SeriesItem {
    @SerializedName("_id")
    public String id;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("title")
    public String title;
}
