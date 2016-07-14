package com.example.rapidbizz2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class loadFromParse extends BroadcastReceiver{

	InputStream inputstream;
	SQLiteDatabase db;
	SQLController dbManager;
	Context context1;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		context1 = context;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if((ni != null) && (ni.isConnected())){
		loadFromRemote();
		Toast.makeText(context, "BC FROM LOAD", Toast.LENGTH_LONG).show();
		}
	}

	private void loadFromRemote() {
		// TODO Auto-generated method stub
		class getFromParse extends AsyncTask<String, Void, String>{

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				String result = null;
				try{
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet("https://api.parse.com/1/classes/Data1");
				request.addHeader("X-Parse-Application-Id", "gmjW7tNFB5x0hUW3T48QWpcDTVNE22UZRBH5r3ql");
                request.addHeader("X-Parse-REST-API-Key", "7blyh8DbYRJKKF2HucpL5uoTftBXNeKwh1fCzeAs");
                request.addHeader("Content-Type", "application/json");
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                inputstream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
				while ((line = reader.readLine()) != null){
                    	sb.append(line + "\n");
                	}
                	result = sb.toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
				finally {
                    try{if(inputstream != null)
                    	inputstream.close();
                    }catch(Exception squish){}
                }
                return result;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				Log.e("resukt",result);
				try {
					JSONObject object = new JSONObject(result);
					JSONArray results = object.getJSONArray("results");
					int n = results.length();
					for(int i=0; i<n; i++){
						JSONObject obj = results.getJSONObject(i);
						String id = obj.getString("id");
						String objectid = obj.getString("objectId");
						String author = obj.getString("author");
						String title = obj.getString("title");
						String subtitle = obj.getString("subtitle");
						String content = obj.getString("content");
						String isdraft = obj.getString("isdraft");
						String otheruser = obj.getString("otheruser");
						String createdat = obj.getString("createdat");
						String updatedat = obj.getString("updatedat");
						checkMySql(id,objectid,author,title,subtitle,content,isdraft,otheruser,createdat,updatedat);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		getFromParse getFromparse = new getFromParse();
        getFromparse.execute();
	}

	@SuppressLint("SimpleDateFormat")
	public void checkMySql(String id,String objectid, String author, String title,
			String subtitle, String content, String isdraft, String otheruser,
			String createdat, String updatedat) {
		// TODO Auto-generated method stub
		DBhelper dbh = new DBhelper(context1);
		db = dbh.getReadableDatabase();
		Cursor c2 = db.rawQuery("SELECT _id, objectid, author, title, subtitle, content, isdraft,"
				+ " otheruser, createdat, updatedat FROM todos WHERE objectid = '" + objectid + "'", null);
		if(c2.moveToFirst()){
			int j = c2.getColumnIndexOrThrow("_id");
			int a = c2.getColumnIndexOrThrow("objectid");
			int b = c2.getColumnIndexOrThrow("author");
			int c = c2.getColumnIndexOrThrow("title");
			int d = c2.getColumnIndexOrThrow("subtitle");
			int e = c2.getColumnIndexOrThrow("content");
			int f = c2.getColumnIndexOrThrow("isdraft");
			int g = c2.getColumnIndexOrThrow("otheruser");
			int h = c2.getColumnIndexOrThrow("createdat");
			int i = c2.getColumnIndexOrThrow("updatedat");
			String id1 = c2.getString(j);
			String objectid1 = c2.getString(a);
			String author1 = c2.getString(b);
			String title1 = c2.getString(c);
			String subtitle1 = c2.getString(d);
			String content1 = c2.getString(e);
			String isdraft1 = c2.getString(f);
			String otheruser1 = c2.getString(g);
			String createdat1 = c2.getString(h);
			String updatedat1 = c2.getString(i);
			java.util.Date start = null;
			java.util.Date end = null;
			try {
				start = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
				 .parse(updatedat);
				end = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(updatedat1);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i1 = 0;
			if(start!= null & end !=null){
				i1 = start.compareTo(end);
			}
			if(i1==1){
			ContentValues contentvalue = new ContentValues();
			contentvalue.put(DBhelper.TODO_OBJECTID, objectid);
			contentvalue.put(DBhelper.TODO_AUTHOR, author);
			contentvalue.put(DBhelper.TODO_TITLE, title);
			contentvalue.put(DBhelper.TODO_SUBTITLE, subtitle);
			contentvalue.put(DBhelper.TODO_CONTENT, content);
			contentvalue.put(DBhelper.TODO_ISDRAFT,isdraft);
			contentvalue.put(DBhelper.TODO_OTHERUSER, otheruser);
			contentvalue.put(DBhelper.TODO_UPDATEDAT, updatedat);
			contentvalue.put(DBhelper.TODO_CREATEDAT, createdat);
			SQLiteDatabase database1 = dbh.getWritableDatabase();
			int i2 = database1.update(DBhelper.TABLE_NAME, contentvalue, DBhelper._ID + "= " + id1, null);
			Log.e("akjbsa", ""+i2);
			}
			
		}else{
			String sql = "INSERT INTO todos(objectid, author, title, subtitle, content, isdraft, otheruser, createdat, updatedat)"
					+ " VALUES ('" + objectid + "', '" + author + "', '" + title + "', '" + subtitle + "', '" + content + "', '" + isdraft + "', '"
					+ otheruser + "', '" + createdat + "', '" + updatedat + "');" ;
			db.execSQL(sql);		
		}
		
	}

	
	
}
