package com.example.rapidbizz2;



import java.util.Calendar;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SyncInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class TodoListActivity extends Activity{

	private SQLController dbcon;
	private ListView listview;
	private TextView loggedInInfoView;
	private SimpleCursorAdapter adapter;
	private ParseUser currentUser;
	private String author;
	String id1;
	private static final int EDIT_ACTIVITY_CODE = 200;
	DBhelper dbh;
	
	final String[] from = new String[] { DBhelper._ID, DBhelper.TODO_TITLE, 
			 DBhelper.TODO_SUBTITLE, DBhelper.TODO_CONTENT, DBhelper.TODO_AUTHOR};
	final int[] to = new int[] {R.id.id1, R.id.title4, R.id.subtitle4, R.id.content4, R.id.author1 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_todo_list);
		 Intent alarmIntent = new Intent(getApplicationContext(), syncToParse.class);
		 Intent alarmIntent1 = new Intent(getApplicationContext(), loadFromParse.class);
	        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
	        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), 5 *1000, pendingIntent);
	        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), 10 *1000, pendingIntent1);
		dbcon = new SQLController(this);
		dbcon.open();
		Cursor cursor = dbcon.fetch();
		listview = (ListView) findViewById(R.id.data_list_view);
		listview.setEmptyView(findViewById(R.id.no_data_view));
		loggedInInfoView = (TextView) findViewById(R.id.loggedin_info);
		dbh = new DBhelper(this);
		SQLiteDatabase db = dbh.getReadableDatabase();
		if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
			currentUser = ParseUser.getCurrentUser();
			author = currentUser.getString("username");
		}else{
			author = null;
		}
		Cursor c1 = db.rawQuery("SELECT _id, title, subtitle, content, author FROM todos WHERE author = '" + author + "'", null);
		adapter = new SimpleCursorAdapter(this, R.layout.activity_items, c1, from, to, 0);
		adapter.notifyDataSetChanged();
		
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView title3 = (TextView) arg1.findViewById(R.id.title4);
				TextView subtitle3 = (TextView) arg1.findViewById(R.id.subtitle4);
				TextView content3 = (TextView) arg1.findViewById(R.id.content4);
				TextView id = (TextView) arg1.findViewById(R.id.id1);
				String title = title3.getText().toString();
				String subtitle = subtitle3.getText().toString();
				String content = content3.getText().toString();
				id1 = id.getText().toString();
				Intent i = new Intent(getApplicationContext(), NewDocument.class);
				i.putExtra("title", title);
				i.putExtra("subtitle", subtitle);
				i.putExtra("content", content);
				i.putExtra("id", id1);
				i.putExtra("author", author);
				startActivityForResult(i,EDIT_ACTIVITY_CODE);
				
			}
		});
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), AddOtherUser.class);
				TextView id = (TextView) arg1.findViewById(R.id.id1);
				i.putExtra("id", id.getText().toString());
				startActivityForResult(i,EDIT_ACTIVITY_CODE);
				return true;
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onPrepareOptionsMenu(menu);
		boolean realUser = !ParseAnonymousUtils.isLinked(ParseUser
				.getCurrentUser());
		menu.findItem(R.id.action_login).setVisible(!realUser);
		menu.findItem(R.id.action_logout).setVisible(realUser);
		menu.findItem(R.id.action_shared).setVisible(realUser);
		menu.findItem(R.id.action_sync).setVisible(realUser);
		menu.findItem(R.id.action_history).setVisible(realUser);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()== R.id.action_new){
			if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
				Intent intent = new Intent(this, LoginSignupActivity.class);
				startActivity(intent);
			}else{
				startActivityForResult(new Intent(this,NewDocument.class),EDIT_ACTIVITY_CODE);
			}
		}
		if(item.getItemId() == R.id.action_history){
			startActivity(new Intent(this,DocumentHistory.class));
		}
		if(item.getItemId() == R.id.action_login){
			Intent intent = new Intent(this, LoginSignupActivity.class);
			startActivity(intent);
		}
		if(item.getItemId() == R.id.action_logout){
			ParseUser.logOut();
			finish();
		}
		if(item.getItemId() == R.id.action_shared){
			Intent intent = new Intent(this, SharedDocument.class);
			startActivity(intent);
		}
		if(item.getItemId() == R.id.action_sync){
			refreshListView();
		}
		return super.onOptionsItemSelected(item);	
	}	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshListView();
		if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
			currentUser = ParseUser.getCurrentUser();
			loggedInInfoView.setText("Welcome," + currentUser.getString("username") );
		}else{
			loggedInInfoView.setText("Not Logged In");
		}
	}
	private void refreshListView() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbh.getReadableDatabase();
		listview.setAdapter(null);
		Cursor c1 = db.rawQuery("SELECT _id, title, subtitle, content, author FROM todos WHERE author = '" + author + "'", null);
		ListAdapter adapter2 = new SimpleCursorAdapter(this, R.layout.activity_items, c1, from, to, 0);
		listview.setAdapter(adapter2);
		((BaseAdapter) adapter2).notifyDataSetChanged();
		Log.e("In resume", "Inresume");
	}
	
	
	
}