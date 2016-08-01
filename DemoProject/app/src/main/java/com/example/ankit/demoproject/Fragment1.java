package com.example.ankit.demoproject;

import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by ankit on 8/1/2016.
 */
public class Fragment1  extends Fragment{
    TableLayout table_layout;
    SQLController sqlcon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment1,container,false);
        table_layout = (TableLayout) view.findViewById(R.id.tableLayout1);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //table_layout = (TableLayout) getView().findViewById(R.id.tableLayout1);
        //table_layout.removeAllViews();
        sqlcon = new SQLController(getActivity());
        buildTable();

    }

    private void buildTable() {
        sqlcon.open();
        Cursor c = sqlcon.readEntry();
        Log.e("cursor", String.valueOf(c));
        Log.e("cursor", String.valueOf(c));
        /*int rows = c.getCount();
        int cols = c.getColumnCount();

        c.moveToFirst();

        // outer for loop
        for (int i = 0; i < rows; i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 0; j < cols; j++) {

                TextView tv = new TextView(getActivity());
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);

                tv.setText(c.getString(j));

                row.addView(tv);

            }

            c.moveToNext();

            table_layout.addView(row);

        }
        */
        sqlcon.close();
    }
    }

