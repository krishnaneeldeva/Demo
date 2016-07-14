package com.example.rapidbizz2;


import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SharedDocument extends Activity{

	private ListView sharedListView;
	private LinearLayout noSharedView;
	private SimpleCursorAdapter adapter;
	DBhelper dbh;
	String author;
	private static final int EDIT_ACTIVITY_CODE = 200;

	final String[] from = new String[] { DBhelper._ID, DBhelper.TODO_TITLE, 
			 DBhelper.TODO_SUBTITLE, DBhelper.TODO_CONTENT, DBhelper.TODO_AUTHOR, DBhelper.TODO_OTHERUSER};
	final int[] to = new int[] {R.id.id1, R.id.title4, R.id.subtitle4, R.id.content4, R.id.author1 , R.id.otheruser1};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shareddocument);
		sharedListView = (ListView) findViewById(R.id.shared_list_view);
		noSharedView = (LinearLayout) findViewById(R.id.no_shared_view);
		sharedListView.setEmptyView(noSharedView);
		ParseUser currentUser = ParseUser.getCurrentUser();
		author = currentUser.getString("username");
		dbh = new DBhelper(this);
		SQLiteDatabase db = dbh.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT _id, title, subtitle, content, author, otheruser FROM todos WHERE otheruser = '" + author + "'", null);
		adapter = new SimpleCursorAdapter(this, R.layout.activity_items, c1, from, to, 0);
		adapter.notifyDataSetChanged();
		
		sharedListView.setAdapter(adapter);
		
		sharedListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView title3 = (TextView) arg1.findViewById(R.id.title4);
				TextView subtitle3 = (TextView) arg1.findViewById(R.id.subtitle4);
				TextView content3 = (TextView) arg1.findViewById(R.id.content4);
				TextView id = (TextView) arg1.findViewById(R.id.id1);
				TextView author = (TextView) arg1.findViewById(R.id.otheruser1);
				String title = title3.getText().toString();
				String subtitle = subtitle3.getText().toString();
				String content = content3.getText().toString();
				String author1 = author.getText().toString();
				String id1 = id.getText().toString();
				Intent i = new Intent(getApplicationContext(), NewDocument.class);
				i.putExtra("title", title);
				i.putExtra("subtitle", subtitle);
				i.putExtra("content", content);
				i.putExtra("id", id1);
				i.putExtra("author", author1);
				startActivityForResult(i,EDIT_ACTIVITY_CODE);
			}
		});
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshListView();
	}
	
	
	
	private void refreshListView() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbh.getReadableDatabase();
		sharedListView.setAdapter(null);
		Cursor c1 = db.rawQuery("SELECT _id, title, subtitle, content, author, otheruser FROM todos WHERE otheruser = '" + author + "'", null);
		ListAdapter adapter2 = new SimpleCursorAdapter(this, R.layout.activity_items, c1, from, to, 0);
		sharedListView.setAdapter(adapter2);
		adapter.notifyDataSetChanged();
	}
	
	

	
}
