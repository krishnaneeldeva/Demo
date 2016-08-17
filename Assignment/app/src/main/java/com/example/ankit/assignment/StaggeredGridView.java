package com.example.ankit.assignment;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ArrayAdapter;

/**
 * Created by ankit on 8/17/2016.
 */
public class StaggeredGridView extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private DecodeLargeBitmaps decodeLargeBitmaps;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staggeredgridview);
        recyclerView = (RecyclerView) findViewById(R.id.staggeredgridview_recyclerview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        String[] dataArray = {"Zesty Chicken", "Spring Rolls", "Apple Pie","Hot Dogs", "Pizza", "Burger", "Windows","Apple", "Android"};
        Bitmap[] drawables = {decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100)};
        adapter = new StaggeredGridViewAdapter(this,dataArray,drawables);
        recyclerView.setAdapter(adapter);
    }
}
