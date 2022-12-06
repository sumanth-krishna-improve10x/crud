package com.improve10x.crud.messages;

public interface onItemActionListener {

    void onItemClicked(Message message);
    void onItemDelete(Message message);
    void onItemEdit(Message message);
}
