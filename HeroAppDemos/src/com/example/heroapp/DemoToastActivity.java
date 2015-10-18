package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DemoToastActivity extends Activity {
	
	private Button tipBtn;
	private EditText editText;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_toast);
		tipBtn = (Button)findViewById(R.id.toastBtn);
		editText = (EditText)findViewById(R.id.toaseMsg);
		
		tipBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				/***
				 * 文本提示
				String toastTip = editText.getText().toString();
				Toast.makeText(DemoToastActivity.this, toastTip, Toast.LENGTH_LONG).show();
				editText.setText("");
				***/
				
				/***
				 * 图像提示
				 **/
				ImageView imgView = new ImageView(DemoToastActivity.this);
				imgView.setImageResource(R.drawable.clound);
				Toast toast = new Toast(DemoToastActivity.this);
				toast.setView(imgView);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
				
			}
		});
	}
}
