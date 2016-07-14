package com.example.rapidbizz2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseUser;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class syncToParse extends BroadcastReceiver{
	
	private ListView historyListView;
	public static final String TABLE_NAME = "todos";
	public static final String _ID = "_id";
	public static final String TODO_AUTHOR = "author";
	public static final String TODO_TITLE = "title";
	public static final String TODO_SUBTITLE = "subtitle";
	public static final String TODO_CONTENT = "content";
	public static final String TODO_ISDRAFT = "isdraft";
	public static final String TODO_OTHERUSER = "otheruser";
	public static final String TODO_UPDATEDAT = "updatedat";
	public static final String TODO_CREATEDAT = "createdat";
	InputStream inputstream;
	SimpleCursorAdapter adapter;
	String _id;
	SQLiteDatabase db;
	final String[] from = new String[] {DBhelper._ID, DBhelper.TODO_OBJECTID, DBhelper.TODO_AUTHOR, DBhelper.TODO_TITLE, 
			 DBhelper.TODO_SUBTITLE, DBhelper.TODO_CONTENT,  DBhelper.TODO_ISDRAFT, DBhelper.TODO_OTHERUSER,
			 DBhelper.TODO_UPDATEDAT, DBhelper.TODO_CREATEDAT };
	final int[] to = new int[] {R.id.id1, R.id.objectid, R.id.author1, R.id.title4, R.id.subtitle4, R.id.content4, R.id.isdraft,
			R.id.otheruser1, R.id.updatedat1, R.id.createdat};
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		DBhelper dbh = new DBhelper(context);
		db = dbh.getReadableDatabase();
		Cursor c2 = db.rawQuery("SELECT _id, objectid, author, title, subtitle, content, isdraft,"
				+ " otheruser, updatedat, createdat FROM todos WHERE isdraft = 'false'" , null);
		Toast.makeText(context, "BC Service Running ", Toast.LENGTH_SHORT).show();
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if((ni != null) && (ni.isConnected())){
		if(c2.moveToFirst()){
			do{
				int a = c2.getColumnIndexOrThrow("_id");
				int b = c2.getColumnIndexOrThrow("author");
				int c = c2.getColumnIndexOrThrow("title");
				int d = c2.getColumnIndexOrThrow("subtitle");
				int e = c2.getColumnIndexOrThrow("content");
				int f = c2.getColumnIndexOrThrow("isdraft");
				int g = c2.getColumnIndexOrThrow("otheruser");
				int h = c2.getColumnIndexOrThrow("updatedat");
				int i = c2.getColumnIndexOrThrow("createdat");
				int j = c2.getColumnIndexOrThrow("objectid");
				 _id = c2.getString(a);
				String author = c2.getString(b);
				String objectid = c2.getString(j);
				String title = c2.getString(c);
				String subtitle = c2.getString(d);
				String content = c2.getString(e);
				String isdraft = "true";
				String otheruser = c2.getString(g);
				String updatedat = c2.getString(h);
				String createdat = c2.getString(i);
				Log.e("me _id", c2.getString(a));
				Log.e("me author", c2.getString(b));
				Log.e("me title", c2.getString(c));
				Log.e("me subtitle", c2.getString(d));
				Log.e("me content", c2.getString(e));
				Log.e("me isdraft", c2.getString(f));
				Log.e("me otheruser", c2.getString(g));
				Log.e("me updatedat", c2.getString(h));
				Log.e("me createdat", c2.getString(i));
//				Log.e("me objectid", c2.getString(j));
				if(!createdat.equals(updatedat)){
					updateIntoDatabase(_id, objectid, author, title, subtitle, content, isdraft, otheruser, updatedat, createdat);
				}else{
					insertIntoDatabase(_id, author, title, subtitle, content, isdraft, otheruser, updatedat, createdat);
				}
			}while(c2.moveToNext());
		}
		}
	}

	private void updateIntoDatabase(String _id1, String objectid,
			String author, String title, String subtitle, String content,
			String isdraft, String otheruser, String updatedat,
			String createdat) {
		// TODO Auto-generated method stub
		class SendPutReqAsyncTask extends AsyncTask<String, Void, String>{

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				String param_id = params[0];
				String paramObjectId = params[1];
				String paramAuthor = params[2];
				String paramTitle = params[3];
				String paramSubtitle = params[4];
				String paramContent = params[5];
				String paramIsDraft = params[6];
				String paramOtherUser = params[7];
				String paramUpdatedat = params[8];
				String paramCreatedat = params[9];
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("id", param_id);
					jsonObject.put("author", paramAuthor);
					jsonObject.put("title", paramTitle);
					jsonObject.put("subtitle", paramSubtitle);
					jsonObject.put("content", paramContent);
					jsonObject.put("isdraft", paramIsDraft);
					jsonObject.put("otheruser", paramOtherUser);
					jsonObject.put("updatedat", paramUpdatedat);
					jsonObject.put("createdat", paramCreatedat);
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				                
                String result = null;
                try{
                	HttpClient httpClient = new DefaultHttpClient();
                	String link = "https://api.parse.com/1/classes/Data1/"+ paramObjectId;
                    HttpPut httpPut = new HttpPut(link);
                    httpPut.addHeader("X-Parse-Application-Id", "gmjW7tNFB5x0hUW3T48QWpcDTVNE22UZRBH5r3ql");
                    httpPut.addHeader("X-Parse-REST-API-Key", "7blyh8DbYRJKKF2HucpL5uoTftBXNeKwh1fCzeAs");
                    httpPut.addHeader("Content-Type", "application/json");
                    httpPut.setEntity(new StringEntity(jsonObject.toString()));
                    HttpResponse response = httpClient.execute(httpPut);
 
                    HttpEntity entity = response.getEntity();
                    inputstream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
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
				String sql = "UPDATE todos SET isdraft = 'true' WHERE _id = '" + _id + "'";
				db.execSQL(sql);
			}
			
			
		}
		SendPutReqAsyncTask sendPutReqAsyncTask = new SendPutReqAsyncTask();
        sendPutReqAsyncTask.execute(_id1, objectid, author, title, subtitle, content, isdraft, otheruser, updatedat, createdat);
	}

	private void insertIntoDatabase(String _id2, String author, String title,
			String subtitle, String content, String isdraft, String otheruser,
			String updatedat, String createdat) {
		// TODO Auto-generated method stub
		class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				String param_id2 = params[0];
				String paramAuthor = params[1];
				String paramTitle = params[2];
				String paramSubtitle = params[3];
				String paramContent = params[4];
				String paramIsDraft = params[5];
				String paramOtherUser = params[6];
				String paramUpdatedat = params[7];
				String paramCreatedat = params[8];
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("id", param_id2);
					jsonObject.put("author", paramAuthor);
					jsonObject.put("title", paramTitle);
					jsonObject.put("subtitle", paramSubtitle);
					jsonObject.put("content", paramContent);
					jsonObject.put("isdraft", paramIsDraft);
					jsonObject.put("otheruser", paramOtherUser);
					jsonObject.put("updatedat", paramUpdatedat);
					jsonObject.put("createdat", paramCreatedat);
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				                
                String result = null;
                try{
                	HttpClient httpClient = new DefaultHttpClient();
                	String link = "https://api.parse.com/1/classes/Data1";
                    HttpPost httpPost = new HttpPost(link);
                    httpPost.addHeader("X-Parse-Application-Id", "gmjW7tNFB5x0hUW3T48QWpcDTVNE22UZRBH5r3ql");
                    httpPost.addHeader("X-Parse-REST-API-Key", "7blyh8DbYRJKKF2HucpL5uoTftBXNeKwh1fCzeAs");
                    httpPost.addHeader("Content-Type", "application/json");
                    httpPost.setEntity(new StringEntity(jsonObject.toString()));
                    HttpResponse response = httpClient.execute(httpPost);
 
                    HttpEntity entity = response.getEntity();
                    inputstream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
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
				JSONObject result1 = null;
				try {
					result1 = new JSONObject(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String id2 = result1.optString("objectId");
				String sql2 = "UPDATE todos SET  isdraft = 'true', objectid = '"+ id2 +"' WHERE _id='"+ _id + "'";
				db.execSQL(sql2);
			}
			
			
		}
		SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(_id2, author, title, subtitle, content, isdraft, otheruser, updatedat, createdat);
	}


	

}
