package com.example.ankit.assignment;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    String[] Tasks = {"Activity Life Cycle","Hello World","Create Database", "Import Database", "Custom List View", "Check Boxes", "Read Database", "Intents And Filters", "Supporting Different Devices", "Play Video", "GIF", "UI Components", "Data Binding"};
    String[] Activities = {"ActivityLifeCycle", "HelloWorld", "CreateDatabase", "ImportDatabase","CustomListView", "CheckBoxes", "ReadDatabase", "IntentsAndFilters","SupportingDifferentDevices", "PlayVideo","Gif","UIComponents", "DataBinding"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.main_listview, Tasks);
        ListView listView = (ListView) findViewById(R.id.Main_List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String activity = "com.example.ankit.assignment."+ Activities[i];
                Log.e("st","v");
                try {
                    Intent intent = new Intent(MainActivity.this, Class.forName(activity));
                    Log.e("Activity Name", String.valueOf(Class.forName(activity)));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Log.e("e", String.valueOf(e));
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String editText = intent.getStringExtra("EditText");
        if(editText!=null& editText!= "+") {
            Toast.makeText(getApplicationContext(), editText, Toast.LENGTH_LONG).show();
        }
        Log.d("MainActivity","OnResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","OnDestroy");
    }

}
