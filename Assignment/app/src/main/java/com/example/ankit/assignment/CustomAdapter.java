package com.example.ankit.assignment;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ankit on 8/4/2016.
 */
public class CustomAdapter {
    Context context;
    ArrayList<String> movies;
    ArrayList<Uri> images;
    public CustomAdapter(Context applicationContext, int custom_list, ArrayList<String> movies, ArrayList<Uri> images){
        super();
    }
    public CustomAdapter(Context context, ArrayList<String> movies, ArrayList<Uri> images){
        super();
        this.context = context;
        this.movies = movies;
        this.images = images;
    }
}

