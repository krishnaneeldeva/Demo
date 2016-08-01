package com.example.ankit.demoproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ankit on 8/1/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String table = "NamePlace";
    public static final String number = "Number";
    public static final String Name = "Name";
    public static final String Place = "Place";
    static final String DB_NAME = "NamePlace.DB";
    static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, table, null, DB_VERSION);
    }

    private static final String CREATE_TABLE = "create table " + table
            + "(" + number + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Name + " TEXT NOT NULL ," + Place
            + " TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + table);
        onCreate(sqLiteDatabase);
    }
}
