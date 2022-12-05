package com.improve10x.crud.messages;

import com.improve10x.crud.messages.Message;

public interface onItemActionListener {

    void onItemClicked(Message message);
    void onItemDelete(Message message);
    void onItemEdit(Message message);
}
