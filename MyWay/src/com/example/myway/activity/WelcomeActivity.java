package com.example.myway.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myway.R;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this, OperateMainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		}, 3000);
	}
}
