package com.improve10x.crud.Quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {
    private OnItemActionListener onItemActionListener;
    private List<Quote> quotes;

    void setOnItemActionListener(OnItemActionListener listener){
        onItemActionListener = listener;
    }

    void setData (List<Quote>quotesList){
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
        Quote quote = quotes.get(position);
        holder.quoteTextTxt.setText(quote.quotes);
        holder.authorNameTxt.setText(quote.authorName);
        if(quote.imageUrl != null && quote.imageUrl.isEmpty() == false) {
            Picasso.get().load(quote.imageUrl).into(holder.quoteImgImg);
        }
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(quote);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(quote);
        });
    }
    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
