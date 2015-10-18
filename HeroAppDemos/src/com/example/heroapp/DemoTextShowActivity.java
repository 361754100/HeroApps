package com.example.heroapp;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heroapp.model.SysUser;

public class DemoTextShowActivity extends Activity implements OnClickListener{
	
	private TextView msgView;
	private Button backBtn;
	private Intent intent;
	private Bundle bundle;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_text_show_activity);
		this.msgView = (TextView)findViewById(R.id.msgView);
		this.backBtn = (Button)findViewById(R.id.backBtn);
		this.backBtn.setOnClickListener(this);
		this.intent = this.getIntent();
		this.bundle = new Bundle();
		List<SysUser> userList = (List<SysUser>) this.intent.getSerializableExtra("userList");
		if(userList != null && userList.size() > 0 ){
			SysUser sysUser = userList.get(0);
			String userMsg = "�û�����"+sysUser.getUsername()
					+"\n���룺"+sysUser.getPassword()
					+"\nע��ʱ�䣺"+sysUser.getRegistTime();
			
			msgView.setText(userMsg);
		}else {
			Toast.makeText(this, "û���յ��κ�����", Toast.LENGTH_LONG);
		}
	}
	
	@Override
	public void onClick(View view) {
		this.bundle.putString("DataCheck", "OK");
		this.intent.putExtra("PostData", this.bundle);
		Toast.makeText(this, "���ص�¼ҳ��", Toast.LENGTH_LONG);
		this.setResult(RESULT_OK, this.intent);
		this.finish();
	}

}
