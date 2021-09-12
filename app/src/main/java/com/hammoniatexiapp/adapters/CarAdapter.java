package com.hammoniatexiapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ItemRideBookBinding;
import com.hammoniatexiapp.pojos.ModelCar;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelCar>arrayList;
    private onCarSelectListener listener;

    public interface onCarSelectListener{
        void onCarSelected(ModelCar car);
    }
    public CarAdapter(Context context, ArrayList<ModelCar>arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public CarAdapter Callback(onCarSelectListener listener){
        this.listener=listener;
        return this;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRideBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_ride_book,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setCar(arrayList.get(position));
        holder.binding.executePendingBindings();
        holder.binding.getRoot().setOnClickListener(v->{
            for (int i=0;i<arrayList.size();i++){
                arrayList.get(i).setSelected(false);
            }
            arrayList.get(position).setSelected(true);
            listener.onCarSelected(arrayList.get(position));
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRideBookBinding binding;
        public MyViewHolder(@NonNull ItemRideBookBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
