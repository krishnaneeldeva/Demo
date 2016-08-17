package com.example.ankit.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ankit on 8/2/2016.
 */
public class SQLController {
    private Context context;
    private DBhelper dBhelper;
    private SQLiteDatabase database;
    public SQLController(Context c){
        context = c;
    }
    public SQLController open() throws SQLException{
        dBhelper = new DBhelper(context);
        database = dBhelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dBhelper.close();
    }
    public void insert(String id, String name, String place){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper._id, id);
        contentValues.put(DBhelper.name, name);
        contentValues.put(DBhelper.place, place);
        database.insert(DBhelper.table_name, null, contentValues);
    }
}
