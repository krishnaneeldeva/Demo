package com.example.rapidbizz2;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

	private Context ourcontext;
	private DBhelper dbHelper;
	private SQLiteDatabase database;
	
	public SQLController(Context c){
		ourcontext = c;
	}
	public SQLController open() throws SQLException{
		
		dbHelper = new DBhelper(ourcontext);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		dbHelper.close();
	}
	public void insert(String objectid, String author, String title, String subtitle, String content, String isdraft, 
			String otheruser, String createdat, String updatedat){
		ContentValues contentvalue = new ContentValues();
		contentvalue.put(DBhelper.TODO_OBJECTID, objectid);
		contentvalue.put(DBhelper.TODO_AUTHOR, author);
		contentvalue.put(DBhelper.TODO_TITLE, title);
		contentvalue.put(DBhelper.TODO_SUBTITLE, subtitle);
		contentvalue.put(DBhelper.TODO_CONTENT, content);
		contentvalue.put(DBhelper.TODO_ISDRAFT, isdraft);
		contentvalue.put(DBhelper.TODO_OTHERUSER, otheruser);
		contentvalue.put(DBhelper.TODO_CREATEDAT, createdat);
		contentvalue.put(DBhelper.TODO_UPDATEDAT, updatedat);
		database.insert(DBhelper.TABLE_NAME, null, contentvalue);
		
	}
	public Cursor fetch(){
		String[] columns = new String[]{ DBhelper._ID, DBhelper.TODO_AUTHOR, DBhelper.TODO_TITLE, 
				 DBhelper.TODO_SUBTITLE, DBhelper.TODO_CONTENT,  DBhelper.TODO_ISDRAFT, DBhelper.TODO_OTHERUSER,
				 DBhelper.TODO_UPDATEDAT, DBhelper.TODO_CREATEDAT};
		Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null,
				null, null, null, null);
		if(cursor!=null){
			cursor.moveToFirst();
		}
		
		return cursor;
		
	}
	public int update(long _id, String author, String title, String subtitle, String content, String isdraft, 
			String otheruser, String updatedat){
		ContentValues contentvalue = new ContentValues();
		contentvalue.put(DBhelper.TODO_AUTHOR, author);
		contentvalue.put(DBhelper.TODO_TITLE, title);
		contentvalue.put(DBhelper.TODO_SUBTITLE, subtitle);
		contentvalue.put(DBhelper.TODO_CONTENT, content);
		contentvalue.put(DBhelper.TODO_ISDRAFT,isdraft);
		contentvalue.put(DBhelper.TODO_OTHERUSER, otheruser);
		contentvalue.put(DBhelper.TODO_UPDATEDAT, updatedat);
		int i = database.update(DBhelper.TABLE_NAME, contentvalue, DBhelper._ID + "= " + _id, null);
				return i;
		
	}
	public void delete(long _id){
		database.delete(DBhelper.TABLE_NAME, DBhelper._ID + "=" + _id, null);
		
	}
}
