package com.example.ankit.assignment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ankit on 8/10/2016.
 */
public class SupportingDifferentDevices extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents_layout);
        ListView listView = (ListView) findViewById(R.id.primary_list);
        TextView textView = (TextView) findViewById(R.id.primary_textView);
        textView.setText("Supporting Different Devices");
        String[] list = {"Supporting Different Languages","Supporting Different Platform Versions", "Supporting Different Devices"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.main_listview, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        SupportingDifferentLanguages supportingDifferentLanguages = new SupportingDifferentLanguages();
                        fragmentTransaction.add(R.id.fragments,supportingDifferentLanguages);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });
    }
}
