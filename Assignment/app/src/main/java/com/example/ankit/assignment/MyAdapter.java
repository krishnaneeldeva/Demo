package com.example.ankit.assignment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by ankit on 8/11/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder1> {
    private String[] dataset;
    private ArrayList<CardView.Album> album;
    String movie;
    int i;
    Integer cover;

    public static class ViewHolder1 extends RecyclerView.ViewHolder{
        public TextView textView,numberView;
        public ImageView imageView;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.cardview_imageView);
            numberView = (TextView) itemView.findViewById(R.id.cardView_numberview);
            textView = (TextView) itemView.findViewById(R.id.cardView_textView);
        }
    }
    public MyAdapter(ArrayList<CardView.Album> dataset) {
        this.album = dataset;
    }

    @Override
    public MyAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_elements,parent,false);
        ViewHolder1 viewHolder = new ViewHolder1(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        CardView.Album album1 = album.get(position);
        holder.textView.setText(album1.getmovie());
        holder.imageView.setImageBitmap(album1.getimage());
        holder.numberView.setText(String.valueOf(album1.getnumber()));
    }



    @Override
    public int getItemCount() {
        return album.size();
    }
}
