package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DemoCheckBoxActivity extends Activity {
	private EditText editTxt;
	private Button btn;
	private CheckBox ckBox;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_checkbox);
		this.editTxt = (EditText)findViewById(R.id.isAgreeText);
		this.btn = (Button)findViewById(R.id.isAgreeBtn);
		this.ckBox = (CheckBox)findViewById(R.id.isAgreeBox);
		
		this.ckBox.setOnClickListener(new CheckBox.OnClickListener(){
			@Override
			public void onClick(View view) {
				if(ckBox.isChecked()){
					editTxt.setText(R.string.isAgree);
				}else {
					editTxt.setText("");
				}
			}
		});
		
		this.btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				if(ckBox.isChecked()){
					Toast.makeText(DemoCheckBoxActivity.this, "成功提交", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(DemoCheckBoxActivity.this, "必须同意服务条款才能注册成功", Toast.LENGTH_LONG).show();
				}
			}
			
		});
	}
}
