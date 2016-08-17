package com.example.ankit.assignment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by ankit on 8/4/2016.
 */
public class CheckBoxes extends Activity{
    ListView simpleListView, customListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] movies = {"WestSide","GillMan","Power Rangers", "Pokemon", "Curse Child", "Eminem", "Love", "Party","This", "Movie", "Worth It", "Mine", "Mumble","Bubble", "Trouble"};
        setContentView(R.layout.checkbox);
        simpleListView = (ListView) findViewById(R.id.simplecheckbox);
        customListView = (ListView) findViewById(R.id.customcheckbox);
        ArrayAdapter listAdapter = new ArrayAdapter(this,R.layout.main_listview,movies);
        simpleListView.setAdapter(listAdapter);
        CustomCheckBox listAdapter1 = new CustomCheckBox(this,R.layout.custom_check_box, movies);
        customListView.setAdapter(listAdapter1);
    }
}
