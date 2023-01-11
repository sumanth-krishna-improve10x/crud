package com.improve10x.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.MessageItemBinding;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private OnItemActionListener onItemActionListener;

     void setOnItemActionListener(OnItemActionListener listener){
        onItemActionListener = listener;
    }

   private List<Message> messageList;

     void setData(List<Message> messages){
        messageList = messages;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageItemBinding binding = MessageItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(binding);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.binding.nameTxt.setText(message.name);
        holder.binding.phonenumberTxt.setText(message.phoneNumber);
        holder.binding.messagesText.setText(message.messagesText);
        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(message);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onItemClicked(message);
        });
    }

    @Override
    public int getItemCount() {
         return messageList.size();
    }
}
