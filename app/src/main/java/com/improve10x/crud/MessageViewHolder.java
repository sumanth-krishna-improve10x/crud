package com.improve10x.crud;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView phoneNumberTxt;
    TextView messageTxt;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        phoneNumberTxt = itemView.findViewById(R.id.phonenumber_txt);
        messageTxt = itemView.findViewById(R.id.messages_txt);
    }
}
