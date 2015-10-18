package com.example.heroapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class SecondChangeActivity extends Activity {
	
	private CalendarView calendar;
	private Button sendBtn;
	private Intent intent;
	private Bundle bundle;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_change_activity2);
		this.calendar = (CalendarView)findViewById(R.id.calendarView1);
		this.sendBtn = (Button)findViewById(R.id.resBtn);
		
		this.intent = this.getIntent();
		this.bundle = intent.getBundleExtra("PostData");
		
		
		this.sendBtn.setOnClickListener(new OnClickListener(){
			@SuppressLint("NewApi")
			public void onClick(View view) {
				int calendarText = calendar.getDateTextAppearance();
				
				bundle.putString("calendarText", "Activity2 calendarText ->"+calendarText);

//				Intent sendIntent = SecondChangeActivity.this.getIntent();
				intent.putExtra("PostData", bundle);
//				
//				startActivity(sendIntent);
				SecondChangeActivity.this.setResult(RESULT_OK, intent);
				SecondChangeActivity.this.finish();
			}
		});
		
		if(bundle != null){
			String msgAc1 = bundle.getString("Picker");
			Toast.makeText(this, msgAc1, Toast.LENGTH_LONG).show();
		}
	}
}
