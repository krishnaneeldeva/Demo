package com.example.ankit.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ankit on 8/2/2016.
 */
public class HelloWorld extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);
        final EditText editText = (EditText) findViewById(R.id.helloworld);
        Button button = (Button) findViewById(R.id.helloworldButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelloWorld.this, MainActivity.class);
                intent.putExtra("EditText", editText.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("Hello world","Back Pressed");
    }
    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Hello world","OnResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Hello world","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Hello world","OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Hello world","OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Hello world","OnDestroy");
    }
}
