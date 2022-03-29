package com.example.app_restaurante.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_restaurante.Interface.ItemClickListener;
import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.Model.Food;
import com.example.app_restaurante.R;

import java.util.ArrayList;

public class AdapterFood extends RecyclerView.Adapter<AdapterFood.ViewHolderFood>{
    ArrayList<Food> listFood;
    public AdapterFood(ArrayList<Food>listCategory){
        this.listFood=listCategory;
    }
    @Override
    public ViewHolderFood onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,null,false);
        return new ViewHolderFood(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderFood holder, int position) {
        holder.assign(listFood.get(position));
    }


    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView dato;
        ImageView image;
        private ItemClickListener itemClickListener;



        public ViewHolderFood(View itemView) {
            super(itemView);
            dato=(TextView)itemView.findViewById(R.id.food_name);
            image=(ImageView)itemView.findViewById(R.id.food_image);
            itemView.setOnClickListener(this);
        }

        public void assign(Food food) {
            dato.setText(food.getName());

        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAbsoluteAdapterPosition(),false);
        }
    }
}
