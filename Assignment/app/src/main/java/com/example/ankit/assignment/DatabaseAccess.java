package com.example.ankit.assignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 8/3/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess (Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }
    public void close(){
        if(database!=null){
            this.database.close();
        }
    }
    public Cursor getQuotes(){
        //List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM employees",null);
        cursor.moveToFirst();
        /*while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }*/
        //cursor.moveToFirst();
        return cursor;
    }
}
