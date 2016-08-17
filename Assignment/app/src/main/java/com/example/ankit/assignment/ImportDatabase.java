package com.example.ankit.assignment;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 8/3/2016.
 */
public class ImportDatabase extends Activity{
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.Main_List);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        //List<String> quotes = databaseAccess.getQuotes();
        //Log.e("me", quotes.get(0));
        List<String> list = new ArrayList<>();
        Cursor cursor = databaseAccess.getQuotes();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.moveToFirst();
        databaseAccess.close();
        createDatabase(cursor);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.main_listview, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("d","dfkjsdf");
                if (mActionMode != null) {
                    return false;
                }
                mActionMode = ImportDatabase.this.startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        });
        //registerForContextMenu(listView);
    }

    private void createDatabase(Cursor cursor) {
        DBhelper nd = new DBhelper(getApplicationContext());
        db = nd.getWritableDatabase();
        int count = cursor.getColumnCount();
        for(int i=0; i<count; i++){
            Log.e("meFuck",cursor.getColumnName(i));
            try {
                db.execSQL("ALTER TABLE new_table ADD COLUMN " + cursor.getColumnName(i) + " string");
                Log.e("Me","New Database Created");
            }catch (SQLException e){
                e.printStackTrace();
            }
            if(i==0){
                while (cursor.moveToNext()) {
                    String sql = "INSERT INTO new_table (" + cursor.getColumnName(i) + ") VALUES (' " + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))) + "');";
                    try {
                        db.execSQL(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                cursor.moveToFirst();
                int j = 0;
                while (cursor.moveToNext()) {
                    j++;
                    String sql1 = "UPDATE new_table SET " + cursor.getColumnName(i) + " = '" + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))) +
                            "' WHERE new_id" + " = '" + j + "';";
                    String sql = "INSERT INTO new_table (" + cursor.getColumnName(i) + ") VALUES (' " + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))) + "');";
                    try {
                        db.execSQL(sql1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Log.e("DoubleFuck", cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))));

                }
            }
            cursor.moveToFirst();
            Log.e("meFuck", String.valueOf(cursor.getCount()));
        }

    }

    private Object mActionMode;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.new_game:
                    Log.d("me","New GAme");
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.help:
                    Log.d("me","HElp");
                    mode.finish();
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
}
