package com.example.ankit.assignment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ankit on 8/11/2016.
 */
public class CardView extends Activity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DecodeLargeBitmaps decodeLargeBitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        String[] Dataset = {"me","myself","and","I"};
        
        Bitmap[] covers = new Bitmap[]{
                decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic1,200,200),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic2,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic3,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic4,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic5,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic6,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic7,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic8,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic9,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic10,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic11,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic12,100,100)
                ,decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic13,100,100),decodeLargeBitmaps.decodeSampledBitmapFromResourse(getResources(),R.drawable.pic14,100,100)};
        ArrayList<Album> list = new ArrayList<Album>();
        Album a;
        a = new Album("Movie1",10,covers[0]);
        list.add(a);
        a = new Album("Movie2",10,covers[1]);
        list.add(a);
        a = new Album("Movie3",10,covers[2]);
        list.add(a);
        a = new Album("Movie4",10,covers[3]);
        list.add(a);
        a = new Album("Movie5",10,covers[4]);
        list.add(a);
        a = new Album("Movie6",10,covers[5]);
        list.add(a);
        a = new Album("Movie7",10,covers[6]);
        list.add(a);
        a = new Album("Movie8",10,covers[7]);
        list.add(a);
        a = new Album("Movie9",10,covers[8]);
        list.add(a);
        a = new Album("Movie10",10,covers[9]);
        list.add(a);
        a = new Album("Movie11",10,covers[10]);
        list.add(a);
        a = new Album("Movie12",10,covers[11]);
        list.add(a);
        a = new Album("Movie13",10,covers[12]);
        list.add(a);
        a = new Album("Movie14",10,covers[13]);
        list.add(a);
        mAdapter = new MyAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class Album {
        String movie;
        int i;
        Bitmap cover;
        public Album(String movie1, int i, Bitmap cover) {
            this.cover= cover;
            this.i = i;
            this.movie = movie1;
        }

        public String getmovie() {
            return movie;
        }

        public Bitmap getimage() {
            return cover;
        }

        public int getnumber() {
            return i;
        }
    }


}
