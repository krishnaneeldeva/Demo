package com.example.ankit.assignment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ankit on 8/17/2016.
 */
public class StaggeredGridViewAdapter extends RecyclerView.Adapter<StaggeredGridViewAdapter.HolderItem>{
    private Context context;
    private String[] dataArray;
    private Bitmap[] drawables;
    public StaggeredGridViewAdapter(Context context, String[] dataArray, Bitmap[] drawables) {
        this.context = context;
        this.dataArray = dataArray;
        this.drawables = drawables;
    }


    @Override
    public StaggeredGridViewAdapter.HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.movie_view,parent,false);
        HolderItem holderItem = new HolderItem(view);

        return holderItem;
    }

    @Override
    public void onBindViewHolder(StaggeredGridViewAdapter.HolderItem holder, int position) {

        holder.imageView.setImageBitmap(drawables[position]);
        holder.textView.setText(dataArray[position]);
    }

    @Override
    public int getItemCount() {
        return dataArray.length;
    }

    public static class HolderItem extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public HolderItem(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.movie_textview);
            imageView = (ImageView) itemView.findViewById(R.id.movie_imageview);
        }
    }
}
