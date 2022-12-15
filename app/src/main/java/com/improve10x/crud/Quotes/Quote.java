package com.improve10x.crud.Quotes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quote implements Serializable {
    @SerializedName("_id")
     String id;
    @SerializedName("quoteText")
     String quotes;
    @SerializedName("authorName")
    String authorName;
    @SerializedName("category")
     String category;
    @SerializedName("imageUrl")
     String imageUrl;
}
