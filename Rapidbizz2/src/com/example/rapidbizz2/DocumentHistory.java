package com.example.rapidbizz2;

import com.parse.ParseUser;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DocumentHistory extends Activity{

	ListView historyListView;
	SQLController sqlcontroller;
	private SQLController dbcon;
	private SimpleCursorAdapter adapter;
	final String[] from = new String[] { DBhelper._ID, DBhelper.TODO_TITLE, 
			 DBhelper.TODO_UPDATEDAT, DBhelper.TODO_AUTHOR };
	final int[] to = new int[] {R.id.id1, R.id.title4, R.id.updatedat1, R.id.otheruser1};
	DBhelper dbh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		dbcon = new SQLController(this);
		dbcon.open();
		Cursor cursor = dbcon.fetch();
		dbh = new DBhelper(this);
		SQLiteDatabase db = dbh.getReadableDatabase();
		String author = ParseUser.getCurrentUser().getString("username");
		Cursor c1 = db.rawQuery("SELECT _id, title, author, updatedat FROM todos WHERE author = '" + author + "'" + "ORDER BY updatedat DESC", null);
		historyListView = (ListView) findViewById(R.id.data_history);
		adapter = new SimpleCursorAdapter(this, R.layout.activity_items, c1, from, to, 0);
		adapter.notifyDataSetChanged();
		historyListView.setAdapter(adapter);		
	}
}
