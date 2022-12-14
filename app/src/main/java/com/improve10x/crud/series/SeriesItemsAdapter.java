package com.improve10x.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {

   private OnItemActionListener onItemActionListener;

     void setOnItemActionListener(OnItemActionListener listener){
        onItemActionListener = listener;
    }

    private List<SeriesItem> seriesList;

     void setData(List<SeriesItem>seriesItems){
        seriesList = seriesItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SeriesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item,parent,false);
        SeriesItemViewHolder seriesViewHolder = new SeriesItemViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesItemViewHolder holder, int position) {
        SeriesItem series = seriesList.get(position);
        holder.titleTxt.setText(series.title);
        if(series.imageUrl != null && series.imageUrl.isEmpty() == false){
            Picasso.get().load(series.imageUrl).into(holder.seriesImg);
        }
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(series);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
