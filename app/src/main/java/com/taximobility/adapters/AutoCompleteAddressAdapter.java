package com.taximobility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.taximobility.Interfaces.onAddressSelectListener;
import com.taximobility.R;
import com.taximobility.databinding.LayoutAddressBinding;
import com.taximobility.pojos.ModelAutoAddress;

import java.util.ArrayList;

public class AutoCompleteAddressAdapter extends RecyclerView.Adapter<AutoCompleteAddressAdapter.MyViewHolder> {
    private onAddressSelectListener listener;
    Context context;
    ArrayList<ModelAutoAddress>data;

    public AutoCompleteAddressAdapter(Context context, ArrayList<ModelAutoAddress>arrayList, onAddressSelectListener listener) {
        this.context = context;
        this.data = arrayList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAddressBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_address,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setAddress(data.get(position));
        holder.binding.executePendingBindings();
        holder.binding.getRoot().setOnClickListener(v->listener.onSelectedAddress(data.get(position)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LayoutAddressBinding binding;
        public MyViewHolder(@NonNull LayoutAddressBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
