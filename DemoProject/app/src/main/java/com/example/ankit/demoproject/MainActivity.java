package com.example.ankit.demoproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    SQLController sqlcon;
    android.app.FragmentManager fragmentManager;
    android.app.FragmentTransaction fragmentTransaction;
    String Name,Place;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.Name);
        final EditText place = (EditText) findViewById(R.id.Place);
        Button submit = (Button) findViewById(R.id.Submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = name.getText().toString();
                Place = place.getText().toString();
                sqlcon = new SQLController(getApplicationContext());
                new MyAsync().execute();
                //Bundle bundle = new Bundle();
                //bundle.putString("Name", Name);
                //bundle.putString("Place",Place);
                //fragmentManager = getFragmentManager();
                 //fragmentTransaction = fragmentManager.beginTransaction();
                //Fragment1 fragment1 = new Fragment1();
                //fragmentTransaction.add(R.id.fragment_container, fragment1);
                //fragmentTransaction.commit();


            }
        });
    }

    private class MyAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            sqlcon.open();
            sqlcon.insertData(Name,Place);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PD = new ProgressDialog(MainActivity.this);
            PD.setTitle("Please Wait..");
            PD.setMessage("Loading...");
            PD.setCancelable(false);
           //PD.show();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            PD.dismiss();
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction.add(R.id.fragment_container, fragment1);
            fragmentTransaction.commit();

        }
    }
}
