package com.example.ankit.assignment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by ankit on 8/2/2016.
 */
public class CreateDatabase extends Activity{
    SQLController sqlcontroller;
    private SQLController dbcon;
    DBhelper dbh;
    SimpleCursorAdapter adapter;
    ListView listview;
    EditText name;
    EditText place;
    Button insert;
    String id;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_database);
        name = (EditText) findViewById(R.id.name);
        place = (EditText) findViewById(R.id.place);
        insert = (Button) findViewById(R.id.insert);
        dbh = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbh.getWritableDatabase();
        listview = (ListView) findViewById(R.id.list1);
        delete = (Button) findViewById(R.id.delete);
        refreshCursor();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n =  insert.getText().toString();
                Log.e("Tag",insert.getText().toString());
                if (insert.getText().toString() == "Insert") {
                    try {
                        dbh = new DBhelper(getApplicationContext());
                        SQLiteDatabase db = dbh.getWritableDatabase();
                        String stringName = name.getText().toString();
                        String stringPlace = place.getText().toString();
                        String sql1 = "insert into  " + DBhelper.table_name + " ( " + DBhelper.name + ", " + DBhelper.place + ") values( '" + stringName + "', '" + stringPlace + "')";
                        String sql = "INSERT INTO " + DBhelper.table_name + " (" + DBhelper.name + ", " + DBhelper.place + ")" +
                                "VALUES ('" + name.getText().toString() + "', '" + place.getText().toString() + "');";
                        db.execSQL(sql);
                        refreshCursor();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e("visibility", String.valueOf(delete.getVisibility()));
                    updateDatabase(name.getText().toString(), place.getText().toString(),id);
                }
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteId(id);
            }
        });

    }

    private void deleteId(String id) {
        dbh = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbh.getWritableDatabase();
        String sql = "DELETE FROM "+DBhelper.table_name+" WHERE "+DBhelper._id+" = '"+id+"'";
        try {
            db.execSQL(sql);
            insert.setText("Insert");
            name.getText().clear();
            place.getText().clear();
            delete.setVisibility(View.GONE);
            refreshCursor();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void refreshCursor(){
        dbh = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbh.getWritableDatabase();
        final Cursor c1 = db.rawQuery("SELECT * FROM " +DBhelper.table_name, null);
        if (c1.moveToFirst()){
            do{
                String data = c1.getString(c1.getColumnIndex("Users_Name"));
                String data1 = c1.getString(c1.getColumnIndex("Users_Place"));
                // do what ever you want here
            }while(c1.moveToNext());
        }
        c1.moveToFirst();
        CursorAdapter todoAdapter = new CursorAdapter(this, c1);

        listview.setAdapter(todoAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                c1.moveToPosition(i);
                String updateName = c1.getString(c1.getColumnIndexOrThrow("Users_Name"));
                String updatePlace = c1.getString(c1.getColumnIndexOrThrow("Users_Place"));
                id = c1.getString(c1.getColumnIndexOrThrow("_id"));
                name.setText(updateName);
                place.setText(updatePlace);
                insert.setText("Update");
                delete.setVisibility(View.VISIBLE);
                //updateDatabase(updateName,updatePlace,id);
            }
        });

    }

    private void updateDatabase(String updateName, String updatePlace, String id) {
        dbh = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbh.getWritableDatabase();
        String sql = "UPDATE "+DBhelper.table_name + " SET " + DBhelper.name + " = '" +updateName + "', "+ DBhelper.place +" = '" + updatePlace + "' WHERE " +
                DBhelper._id + " = '" + id + "'";
        try {
            db.execSQL(sql);
            insert.setText("Insert");
            name.getText().clear();
            place.getText().clear();
            refreshCursor();
            delete.setVisibility(View.GONE);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public class CursorAdapter extends android.support.v4.widget.CursorAdapter{

        public CursorAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.database_view,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView name = (TextView) view.findViewById(R.id.label1);
            TextView place = (TextView) view.findViewById(R.id.label2);
            String body = cursor.getString(cursor.getColumnIndexOrThrow("Users_Name"));
            String body1 = cursor.getString(cursor.getColumnIndexOrThrow("Users_Place"));
            name.setText(body);
            place.setText(body1);
        }
    }
}
