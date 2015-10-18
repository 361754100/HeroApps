package com.example.heroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class DemoChangeActivity extends Activity {
	
	private Button sendBtn;
	private DatePicker dPicker;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_change_activity1);
		this.sendBtn = (Button)findViewById(R.id.changeSendBtn);
		this.dPicker = (DatePicker)findViewById(R.id.changeDatePicker);
		
		this.sendBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				int year = dPicker.getYear();
				Bundle bundle = new Bundle();
				bundle.putString("Picker", "Activity1 Picker year->"+year);
				Intent intent = new Intent();
				intent.setClass(DemoChangeActivity.this, SecondChangeActivity.class);
				intent.putExtra("PostData", bundle);
				
//				startActivity(intent);
				startActivityForResult(intent, 200);
			}
		});
		
//		Intent intent = this.getIntent();
//		Bundle bundle = intent.getBundleExtra("PostData");
//		if(bundle != null){
//			String msgAc2 = bundle.getString("calendarText");
//			Toast.makeText(this, msgAc2, Toast.LENGTH_LONG).show();
//		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		switch(resultCode){
			case RESULT_OK: 
				Bundle bundle = data.getBundleExtra("PostData");
				if(bundle != null){
					String msgAc2 = bundle.getString("calendarText");
					Toast.makeText(this, msgAc2, Toast.LENGTH_LONG).show();
				}
		}
	}
}
