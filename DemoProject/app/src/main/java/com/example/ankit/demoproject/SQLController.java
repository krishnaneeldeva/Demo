package com.example.ankit.demoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by ankit on 8/1/2016.
 */
public class SQLController {
    private DbHelper dbHelper;
    private Context context1;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        context1 = c;
    }
    public SQLController open() throws SQLException{
        dbHelper = new DbHelper(context1);
        database =  dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insertData(String Name, String Place){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.Name, Name);
        contentValues.put(DbHelper.Place, Place);
        try{
            database.insert(DbHelper.table, null, contentValues);
        }catch (SQLException e){
            Log.e("ecs", String.valueOf(e));
        }
    }

    public Cursor readEntry(){
        String[] allColumns = new String[]{dbHelper.number,dbHelper.Name, DbHelper.Place};
        Log.e("ME", String.valueOf(allColumns));
        Cursor c = database.query(DbHelper.table, allColumns, null, null ,null, null, null);
        Log.e("cursor", String.valueOf(c));
        if (c.moveToFirst()){
            do{
                String data = c.getString(c.getColumnIndex("Name"));
                Log.e("data",data);
                // do what ever you want here
            }while(c.moveToNext());
        }
        if(c!=null){
            c.moveToFirst();

        }
        return c;
    }
}
