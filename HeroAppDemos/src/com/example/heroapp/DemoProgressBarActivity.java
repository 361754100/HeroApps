package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class DemoProgressBarActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.demo_progressbar);
		Button btn1 = (Button)findViewById(R.id.showBtn);
		Button btn2 = (Button)findViewById(R.id.hideBtn);
		
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View source){
				setProgressBarIndeterminateVisibility(true);
				setProgressBarVisibility(true);
				setProgress(4500);
			}
		});
		
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View source){
				setProgressBarIndeterminateVisibility(false);
				setProgressBarVisibility(false);
			}
		});
	}
}
