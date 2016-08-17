package com.example.ankit.assignment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

/**
 * Created by ankit on 8/10/2016.
 */
public class SupportingDifferentLanguages extends Fragment {
    Resources res;
    String[] list = {"Spanish","French","Chinese","Telugu", "Hindi"};
    String[] locale1 = {"es","fr","zh","te","hi"};
    ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.different_languages, container, false);
        final TextView textView = (TextView) v.findViewById(R.id.language_textView);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        textView.setText(R.string.hello);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Locale locale = new Locale(locale1[i]);
                Locale.setDefault(locale);
                Configuration configuration = res.getConfiguration();
                configuration.locale = locale;
                res.updateConfiguration(configuration,res.getDisplayMetrics());
                textView.setText(R.string.hello);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText(R.string.hello);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        res = context.getResources();
        adapter = new ArrayAdapter<String>(context,R.layout.main_listview,list);
    }
}
