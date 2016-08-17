package com.example.ankit.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ankit on 8/11/2016.
 */
public class UIComponents extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents_layout);
        ListView listView = (ListView) findViewById(R.id.primary_list);
        TextView textView = (TextView) findViewById(R.id.primary_textView);
        textView.setText("UI Components");
        String[] arr = {"Card View","Grid View","Staggered Grid View"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.main_listview,arr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent k = new Intent(UIComponents.this,CardView.class);
                        startActivity(k);
                        break;
                    case 1:
                        Intent o = new Intent(UIComponents.this, Gridview.class);
                        startActivity(o);
                        break;
                    case 2:
                        Intent j = new Intent(UIComponents.this,StaggeredGridView.class);
                        startActivity(j);
                        break;
                }
            }
        });
    }
}
