package com.improve10x.crud.Quotes;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    EditText quoteTxt;
    EditText authorTxt;
    EditText categoryTxt;
    EditText  quoteImgUrl;
    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        quoteTxt = itemView.findViewById(R.id.quote_txt);

    }
}
