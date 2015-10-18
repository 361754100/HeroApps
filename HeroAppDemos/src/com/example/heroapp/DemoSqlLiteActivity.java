package com.example.heroapp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heroapp.model.SysUser;

public class DemoSqlLiteActivity extends Activity implements OnClickListener{
	
	private EditText username;
	private EditText password;
	private Button loginBtn;
	
	private SQLiteDatabase sqlLiteDB;
	private DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_sqllite_activity);
		
		username = (EditText)findViewById(R.id.upc_username);
		password = (EditText)findViewById(R.id.upc_password);
		loginBtn = (Button)findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(this);
		
		//建立数据库并创建表SYS_USERS
		sqlLiteDB = this.openOrCreateDatabase("upc.db", MODE_PRIVATE, null);
		String ddl = "create table if not exists SYS_USERS " +
				" (ID integer primary key autoincrement," +
				" USERNAME varchar(50), " +
				" PASSWORD varchar(16), " +
				" REGISTTIME varchar(20)) ";
		sqlLiteDB.execSQL(ddl);
		
		//插入数据
		SysUser user = new SysUser();
		user.setUsername(username.getText().toString());
		user.setPassword(password.getText().toString());
		user.setRegistTime(dFormat.format(new Date()));
		
		String dml = "insert into SYS_USERS (USERNAME, PASSWORD, REGISTTIME) values(?,?,?)";
		sqlLiteDB.execSQL(dml, new Object[]{user.getUsername(), user.getPassword(), user.getRegistTime()});
		
	}

	@Override
	public void onClick(View view) {
		SysUser temp = null;
		//查询数据
		String sql = "select * from SYS_USERS where USERNAME =?";
		Cursor cursor = sqlLiteDB.rawQuery(sql, new String[]{"user1"});
		while(cursor.moveToNext()){
			temp = new SysUser();
			temp.setId(cursor.getInt(cursor.getColumnIndex("ID")));
			temp.setUsername(cursor.getString(cursor.getColumnIndex("USERNAME")));
			temp.setPassword(cursor.getString(cursor.getColumnIndex("PASSWORD")));
			temp.setRegistTime(cursor.getString(cursor.getColumnIndex("REGISTTIME")));
		}
		cursor.close();
//		sqlLiteDB.close();
		
		Intent intent = new Intent();
		intent.setClass(this, DemoTextShowActivity.class);
		List<SysUser> userList = new ArrayList<SysUser>();
		userList.add(temp);
		intent.putExtra("userList", (Serializable)userList);
		
		startActivityForResult(intent, 200);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		switch(resultCode){
			case RESULT_OK: 
				Bundle bundle = data.getBundleExtra("PostData");
				if(bundle != null){
					String msgAc2 = bundle.getString("DataCheck");
					Toast.makeText(this, msgAc2, Toast.LENGTH_LONG).show();
				}
		}
	}
}
