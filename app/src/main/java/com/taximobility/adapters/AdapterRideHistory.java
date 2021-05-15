package com.taximobility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.taximobility.R;
import com.taximobility.databinding.ItemRideHistoryBinding;
import com.taximobility.pojos.ModelRideHistory;

import java.util.ArrayList;


public class AdapterRideHistory extends RecyclerView.Adapter<AdapterRideHistory.MyViewHolder> {
    private final onClickHistory listener;
    Context context;
    ArrayList<ModelRideHistory.Result>list;

    public AdapterRideHistory(Context context, ArrayList<ModelRideHistory.Result> list,onClickHistory listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }
    public interface onClickHistory{
        void onClickView(ModelRideHistory.Result result);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRideHistoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_ride_history,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setData(list.get(position));
        holder.binding.executePendingBindings();
        holder.itemView.setOnClickListener(v->{
            listener.onClickView(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRideHistoryBinding binding;
        public MyViewHolder(@NonNull ItemRideHistoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
