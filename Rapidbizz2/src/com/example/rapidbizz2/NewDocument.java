package com.example.rapidbizz2;


import java.util.Date;

import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewDocument extends Activity{

	private SQLController dbManager;
	private String dataId, author1 = null;
	private String newDocument = null;
	private EditText titleText,subTitleText,contentText;
	private Button saveButton, deleteButton;
	private long _id;
	DBhelper dbh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newdocument);
		dbManager = new SQLController(this);
		dbManager.open();
		dbh = new DBhelper(this);
		if(getIntent().hasExtra("id")){
			dataId = getIntent().getExtras().getString("id");
		}
		if(getIntent().hasExtra("author")){
			author1 = getIntent().getExtras().getString("author");
		}
		titleText = (EditText) findViewById(R.id.title1);
		subTitleText = (EditText) findViewById(R.id.subtitle1);
		contentText = (EditText) findViewById(R.id.content1);
		saveButton = (Button) findViewById(R.id.saveButton);
		deleteButton = (Button) findViewById(R.id.deleteButton);
		if(dataId==null){
			newDocument="1";
		}else{
			Intent intent = getIntent();
			String id = intent.getStringExtra("id");
			String title = intent.getStringExtra("title");
			String subtitle = intent.getStringExtra("subtitle");
			String content = intent.getStringExtra("content");
			_id = Long.parseLong(id);
			titleText.setText(title);
			subTitleText.setText(subtitle);
			contentText.setText(content);
			deleteButton.setVisibility(View.VISIBLE);
		}
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date updatedat = new Date();
				String title = titleText.getText().toString();
				String subtitle = subTitleText.getText().toString();
				String content = contentText.getText().toString();
				ParseUser currentUser = ParseUser.getCurrentUser();
				String author = currentUser.getString("username");
				if(newDocument=="1"){
					dbManager.insert("", author, title, subtitle, content, "false", "", updatedat.toString(),updatedat.toString());
					Intent main = new Intent(getApplicationContext(), TodoListActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(main);
				}
				else{
					SQLiteDatabase db = dbh.getWritableDatabase();
					String sql = "UPDATE todos SET isdraft='false', title ='" + title + "' , subtitle ='" + subtitle + 
							"' , content ='" + content + "' , updatedat ='" + updatedat.toString() + "' WHERE _id ='" + _id + "';";
					db.execSQL(sql);
				this.returnHome();
				}
			}
			public void returnHome() {
				Intent home_intent = new Intent(getApplicationContext(), TodoListActivity.class)
						.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(home_intent);
			}
		});
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbManager.delete(_id);
				this.returnHome();
			}
			public void returnHome() {
				Intent home_intent = new Intent(getApplicationContext(), TodoListActivity.class)
						.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(home_intent);
			}
		});
	}
	

}
