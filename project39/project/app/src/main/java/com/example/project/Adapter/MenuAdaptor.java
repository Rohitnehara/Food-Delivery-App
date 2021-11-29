package com.example.project.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activity.ShowDetailActivity;
import com.example.project.Domain.FoodDomain;
import com.example.project.R;

import java.util.ArrayList;

public class MenuAdaptor extends RecyclerView.Adapter<MenuAdaptor.myViewHolder> {
    ArrayList <FoodDomain> datalist;

    public MenuAdaptor(ArrayList<FoodDomain> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
  holder.title2Txt.setText(datalist.get(position).getTitle());
  holder.totalEachItem.setText(String.valueOf(datalist.get(position).getFee()));
  holder.picCard.setImageURI(Uri.parse(datalist.get(position).getPic()));
//        holder.totalEachItem.setText(String.valueOf(Math.round((datalist.get(position).getNumberInCart() * datalist.get(position).getFee()) * 100.0) / 100.0));
//        holder.num.setText(String.valueOf(datalist.get(position).getNumberInCart()));
      //  holder.totalEachItem.setText(String.valueOf(Math.round((datalist.get(position).getNumberInCart() * datalist.get(position).getFee()) * 100.0) / 100.0));\
        Glide.with(holder.picCard.getContext()).load(datalist.get(position).getPic()).into(holder.picCard);


        holder.addbtn.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(),ShowDetailActivity.class);
               intent.putExtra("object",datalist.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
       });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {
       TextView title2Txt,totalEachItem,addbtn,feeEachItem,num;
               ImageView picCard;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            picCard=itemView.findViewById(R.id.picCard);
            title2Txt=itemView.findViewById(R.id.title2Txt);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);

            num = itemView.findViewById(R.id.numberItemTxt);

        addbtn=itemView.findViewById(R.id.adddingbtn);

feeEachItem =itemView.findViewById(R.id.feeEachItem);


        }
    }
}
