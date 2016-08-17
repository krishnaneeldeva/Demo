package com.example.ankit.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ankit on 8/9/2016.
 */
public class ImplicitIntent extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.implicit_layout);
        Intent intent = getIntent();
        String x = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView textView = (TextView) findViewById(R.id.implicit_textview);
        textView.setText(x);
    }
}
