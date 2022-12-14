package com.improve10x.crud.series;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Target;

import java.io.Serializable;

public class SeriesItem  implements Serializable {
    @SerializedName("_id")
     String id;
     String seriesId;
    @SerializedName("imageUrl")
     String imageUrl;
    @SerializedName("title")
     String title;
}
