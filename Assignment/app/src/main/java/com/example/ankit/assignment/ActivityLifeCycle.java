package com.example.ankit.assignment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by ankit on 8/2/2016.
 */
public class ActivityLifeCycle extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.d("ActivityLifeCycle","OnCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ActivityLifeCycle","OnResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ActivityLifeCycle","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ActivityLifeCycle","OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ActivityLifeCycle","OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ActivityLifeCycle","OnDestroy");
    }
}
