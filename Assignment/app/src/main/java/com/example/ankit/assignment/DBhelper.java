package com.example.ankit.assignment;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ankit on 8/2/2016.
 */
public class DBhelper extends SQLiteOpenHelper{
    public static final String table_name = "Users";
    public static final String _id = "_id";
    public static final String name = "Users_Name";
    public static final String place = "Users_Place";
    static final String DB_NAME = "Users.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " + table_name + " (" + _id +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + name + " TEXT NOT NULL, " + place + " TEXT NOT NULL );";
    private static final String CREATE_TABLE1 = "CREATE TABLE " + "new_table" + " (" + "new_id" +
            " INTEGER PRIMARY KEY AUTOINCREMENT);";

    DBhelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(sqLiteDatabase);
    }

}
