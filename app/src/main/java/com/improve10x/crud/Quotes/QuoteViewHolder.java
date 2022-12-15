package com.improve10x.crud.Quotes;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    ImageView quoteImgImg;
    TextView quoteTextTxt;
    TextView authorNameTxt;
    ImageButton deleteBtn;
    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
       quoteImgImg = itemView.findViewById(R.id.quotesimg_img);
       quoteTextTxt = itemView.findViewById(R.id.quote_Text_txt);
       authorNameTxt = itemView.findViewById(R.id.authorname_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
