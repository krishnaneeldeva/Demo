package com.example.ankit.assignment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class CustomListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> movies;
    private final int layoutResourseId;
    private final ArrayList<Bitmap> images;

    public CustomListViewAdapter(Context applicationContext, int custom_list, ArrayList<String> movies, ArrayList<Bitmap> images) {
        super(applicationContext,R.layout.movie_view, custom_list);
        Log.d("yo","in here: "+ movies.get(0));
        this.context = applicationContext;
        this.layoutResourseId = custom_list;
        this.images = images;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList<String> a = movies;
        Log.d("yo","sdfsd: "+a.get(0));
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.movie_view,parent,false);
        TextView tv = (TextView)customView.findViewById(R.id.movie_textview);
        ImageView iv = (ImageView)customView.findViewById(R.id.movie_imageview);
        String movie = movies.get(position);
        Log.d("MEF", (movie));
        Bitmap image = images.get(position);
        Log.d("MCVF", String.valueOf(images.size()));
        tv.setText(movie);
        iv.setImageURI(null);

        iv.setImageBitmap(image);

        return  customView;
//        return super.getView(position, convertView, parent);
    }


    @Override
    public int getCount() {
        return movies.size();
    }
}
