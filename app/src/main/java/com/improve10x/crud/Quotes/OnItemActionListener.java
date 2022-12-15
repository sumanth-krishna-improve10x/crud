package com.improve10x.crud.Quotes;

public interface OnItemActionListener {
    void onItemClicked(Quote quote);
    void onItemDelete(Quote quote);
    void onItemEdit(Quote quote);
}
