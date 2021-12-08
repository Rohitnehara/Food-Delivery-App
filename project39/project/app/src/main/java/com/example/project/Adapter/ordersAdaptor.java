package com.example.project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Domain.FoodDomain;
import com.example.project.R;

import java.util.ArrayList;

public class ordersAdaptor extends RecyclerView.Adapter<ordersAdaptor.myViewHolder> {
    ArrayList <FoodDomain> datalist;
    public ordersAdaptor(ArrayList<FoodDomain> datalist) {
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.title.setText(datalist.get(position).getTitle());
        holder.price.setText(String.valueOf(datalist.get(position).getFee()));
        holder.count.setText(String.valueOf(datalist.get(position).getNumberInCart()));

        Glide.with(holder.imgl.getContext()).load(datalist.get(position).getPic()).into(holder.imgl);

    }


    @Override
    public int getItemCount() {
        return 0;
    }
    public class myViewHolder extends RecyclerView.ViewHolder
    {
    TextView title,price, total,count;
    ImageView imgl;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title2Txt);
            price=itemView.findViewById(R.id.feeEachItem);
            total=itemView.findViewById(R.id.totalEachItem);
            count =itemView.findViewById(R.id.numberItemTxt);
            imgl=itemView.findViewById(R.id.picCard);
        }
    }
}
