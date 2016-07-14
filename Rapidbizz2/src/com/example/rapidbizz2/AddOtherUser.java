package com.example.rapidbizz2;


import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddOtherUser extends Activity{

	private long _id;
	ListView otherUsersList;
	Button addUsersButton;
	EditText addUsersText;
	private LinearLayout noUsersView;
	private SQLController dbcon;
	DBhelper dbh;
	private String dataId = null;
	private SimpleCursorAdapter adapter;
	final String[] from = new String[] { DBhelper._ID, DBhelper.TODO_TITLE,  
			 DBhelper.TODO_SUBTITLE, DBhelper.TODO_CONTENT, DBhelper.TODO_OTHERUSER, DBhelper.TODO_UPDATEDAT};
	final int[] to = new int[] {R.id.id, R.id.title3, R.id.subtitle3, R.id.content3, R.id.otheruser, R.id.updatedat };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);
		otherUsersList = (ListView) findViewById(R.id.add_users_list);
		noUsersView = (LinearLayout) findViewById(R.id.no_users_view);
		otherUsersList.setEmptyView(noUsersView);
		addUsersButton = (Button) findViewById(R.id.add_users_button);
		addUsersText = (EditText) findViewById(R.id.add_user_text);
		if(getIntent().hasExtra("id")){
			dataId = getIntent().getExtras().getString("id");
		}
		dbh = new DBhelper(this);
		SQLiteDatabase db = dbh.getReadableDatabase();
		Cursor c = db.query(DBhelper.TABLE_NAME, new String[]{"_id", "title", "subtitle", "content", "otheruser", "updatedat"},
				" _id = "+ dataId, null, null, null, null);
		dbcon = new SQLController(this);
		dbcon.open();
		Cursor cursor = dbcon.fetch();
		adapter = new SimpleCursorAdapter(this, R.layout.activity_items_add_user, c, from, to, 0);
		adapter.notifyDataSetChanged();
		
		otherUsersList.setAdapter(adapter);
		
		addUsersButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date updatedat = new Date();
				String adduser = addUsersText.getText().toString();
				SQLiteDatabase db = dbh.getReadableDatabase();
				String query = " UPDATE " + DBhelper.TABLE_NAME + " SET isdraft = 'false', updatedat = '" + updatedat.toString() +"',otheruser = '"+ adduser + "' WHERE _id ="+ dataId;
				db.execSQL(query);
				Toast.makeText(getApplicationContext(), dataId, Toast.LENGTH_LONG);
				setResult(Activity.RESULT_OK);
				finish();			
			}
		});
	}
	

}
