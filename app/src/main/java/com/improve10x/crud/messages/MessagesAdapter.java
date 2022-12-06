package com.improve10x.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    public com.improve10x.crud.messages.onItemActionListener onItemActionListener;

    public void setOnItemActionListener(onItemActionListener listener){
        onItemActionListener = listener;
    }

    List<Message> messagesList;

    public void setUpData (List<Message> messageArrayList){
        messagesList = messageArrayList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messagesList.get(position);
        holder.nameTxt.setText(message.name);
        holder.phoneNumberTxt.setText(message.phoneNumber);
        holder.messageTxt.setText(message.messagesText);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(message);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(message);
        });
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
