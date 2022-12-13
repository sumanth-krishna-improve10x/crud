package com.improve10x.crud.messages;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView phoneNumberTxt;
    TextView messageTextTxt;
    ImageView deleteBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        phoneNumberTxt = itemView.findViewById(R.id.phonenumber_txt);
        messageTextTxt = itemView.findViewById(R.id.messages_text);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
