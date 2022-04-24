package com.example.app_restaurante.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_restaurante.FoodListActivity;
import com.example.app_restaurante.Interface.ItemClickListener;
import com.example.app_restaurante.MainActivity;
import com.example.app_restaurante.Model.Category;
import com.example.app_restaurante.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolderCategory>{

    ArrayList<Category> listCategory;
    public ViewHolderCategory viewHolderCategory;
    final AdapterCategory.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(Category cat);
    }
    public AdapterCategory(ArrayList<Category>listCategory, AdapterCategory.OnItemClickListener listener){
        this.listCategory=listCategory;
        this.listener=listener;
    }
    @Override
    public ViewHolderCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,null,false);
        viewHolderCategory= new ViewHolderCategory(view);
        return viewHolderCategory;
    }

    @Override
    public void onBindViewHolder(ViewHolderCategory holder, int position) {
        holder.assign(listCategory.get(position));

    }



    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class ViewHolderCategory extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView dato;
        ImageView image;
        private ItemClickListener itemClickListener;
        View itemView;


        public ViewHolderCategory(View itemView) {
            super(itemView);
            this.itemView=itemView;
            dato=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        public void assign(Category category) {
            dato.setText(category.getName());
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.onItemClick(category);
                }
            });

        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAbsoluteAdapterPosition(),false);
        }
        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView bmImage;

            public DownloadImageTask(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mIcon11;
            }

            protected void onPostExecute(Bitmap result) {
                bmImage.setImageBitmap(result);
            }
        }
    }
}
