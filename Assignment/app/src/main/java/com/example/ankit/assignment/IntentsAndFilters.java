package com.example.ankit.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ankit on 8/9/2016.
 */
public class IntentsAndFilters extends Activity{
    String[] array = {"Explicit Intent", "Implicit Intent"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents_layout);
        ListView listView = (ListView) findViewById(R.id.primary_list);
        TextView textView = (TextView) findViewById(R.id.primary_textView);
        textView.setText("Intents And Filters");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.main_listview,array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(IntentsAndFilters.this,ExplicitIntent.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent();
                        intent1.setAction(Intent.ACTION_SEND);
                        intent1.putExtra(Intent.EXTRA_TEXT,"Hello");
                        intent1.setType("text/plain");
                        Intent chooser = Intent.createChooser(intent1,"Select to have fun");
                        if (intent1.resolveActivity(getPackageManager()) != null){
                            startActivity(chooser);
                            break;
                        }
                }
            }
        });
    }
}
