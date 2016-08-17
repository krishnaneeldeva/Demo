package com.example.ankit.assignment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ankit on 8/4/2016.
 */
public class CustomListView extends Activity{
    ListView listView;
    Button insert;
    ImageView picture = null;
    EditText name;
    ArrayList<Bitmap> images;
    String pictureImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list);
        picture = (ImageView) findViewById(R.id.movie_pic);
        name = (EditText) findViewById(R.id.movie_name);
        insert = (Button) findViewById(R.id.movie_insert);
        listView = (ListView) findViewById(R.id.movie_list);
        final ArrayList<String> movies = new ArrayList<String>();
        images = new ArrayList<Bitmap>();

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture.setImageResource(R.drawable.pic8);
                String string = name.getText().toString();
                movies.add(string);
                Log.d("Me", String.valueOf(movies));
                ListAdapter customListView = new CustomListViewAdapter(getApplicationContext(),R.layout.movie_view, movies, images);
                String[] arr = {"sdf","123","xyz"};
//                ListAdapter customListView = new ArrayAdapter<String>(getApplicationContext(),R.layout.layout_textview_for_listview,arr);
                listView.setAdapter(customListView);
                /*if(picture == null){
                    Toast.makeText(CustomListView.this,"Please Click the Photo",Toast.LENGTH_LONG).show();
                }else {
                    CustomListViewAdapter customListView = new CustomListViewAdapter(getApplicationContext(),R.layout.movie_view, movies, images);
                    listView.setAdapter(customListView);
                    picture = null;
                }*/

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap mImageBitmap = (Bitmap) extras.get("data");
                    images.add(mImageBitmap);
                    picture.setImageBitmap(mImageBitmap);
                    Log.e("mvds", String.valueOf(images.size()));
                }
            }
        }
    }
