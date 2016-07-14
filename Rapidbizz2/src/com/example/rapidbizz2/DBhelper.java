package com.example.rapidbizz2;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{

	public static final String TABLE_NAME = "todos";
	public static final String _ID = "_id";
	public static final String TODO_OBJECTID = "objectid";
	public static final String TODO_AUTHOR = "author";
	public static final String TODO_TITLE = "title";
	public static final String TODO_SUBTITLE = "subtitle";
	public static final String TODO_CONTENT = "content";
	public static final String TODO_ISDRAFT = "isdraft";
	public static final String TODO_OTHERUSER = "otheruser";
	public static final String TODO_UPDATEDAT = "updatedat";
	public static final String TODO_CREATEDAT = "createdat";
	static final String DB_NAME = "TODOS.DB";
	static final int DB_VERSION = 1;
	private static final String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + "(" + _ID 
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_OBJECTID + " TEXT, " + TODO_AUTHOR + " TEXT, "
			+ TODO_TITLE + " TEXT, " + TODO_SUBTITLE + " TEXT, " + TODO_CONTENT + " TEXT, "
			+ TODO_ISDRAFT + " TEXT, " + TODO_OTHERUSER + " TEXT, " + TODO_UPDATEDAT + " DATETIME, " 
			+ TODO_CREATEDAT + " DATETIME" + ")";
	
	public DBhelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

	
}
