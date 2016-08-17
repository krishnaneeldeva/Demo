package com.example.ankit.assignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ankit on 8/4/2016.
 */
public class CustomCheckBox extends ArrayAdapter<String>{
    String[] string1;
    Context context;
    int position1;
    public CustomCheckBox(Context context, int resource, String[] movies) {
        super(context, resource);
        Log.d("ncd","nkvdfs");
        this.context = context;
        this.string1 = movies;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Log.d("ncd","nkvdfcsacsacsas");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.custom_check_box,parent,false);
        final CheckBox checkBox = (CheckBox) customView.findViewById(R.id.customcheckboxincheckbox);
        TextView textView = (TextView) customView.findViewById(R.id.checkboxtext);
        textView.setText(this.string1[position]);
        this.position1 = position;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    Toast.makeText(context, string1[position], Toast.LENGTH_LONG).show();
//                Toast.makeText(context,Integer.toString(position1),Toast.LENGTH_LONG).show();
                }
            }
        });
        if(checkBox.isChecked()){
            Toast.makeText(context,position,Toast.LENGTH_LONG).show();
        };
        return customView;
    }

    @Override
    public int getCount() {
        return string1.length;
    }
}
