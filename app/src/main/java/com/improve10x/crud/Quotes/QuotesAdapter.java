package com.improve10x.crud.Quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {
    private List<Quotes> quotes;

    void setData (List<Quotes>quotesList){
        quotes = quotesList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_item,parent,false);
        QuoteViewHolder quoteViewHolder = new QuoteViewHolder(view);
        return quoteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
