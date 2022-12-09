package com.improve10x.crud.series;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Target;

public class SeriesItem {
    @SerializedName("_id")
     String id;
     String seriesId;
    @SerializedName("imageUrl")
     String imageUrl;
    @SerializedName("title")
     String title;
}
