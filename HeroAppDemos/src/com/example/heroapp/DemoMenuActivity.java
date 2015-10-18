package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DemoMenuActivity extends Activity {
	
	private Spinner citySpinner;
	private Animation menuAnim;
	private static String citys[] = {"北京市","上海市","广州市","东莞市"};
	private ArrayAdapter<String> adapter ;
	private TextView menuText;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_menu_spinner);
		
		this.menuText = (TextView)findViewById(R.id.menuSelected);
		this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citys);
		this.adapter.setDropDownViewResource(R.layout.demo_menu_dropdown);

		this.citySpinner = (Spinner)findViewById(R.id.citySpinner);
		this.citySpinner.setAdapter(adapter);
		this.menuAnim = AnimationUtils.loadAnimation(this, R.anim.menu_anim);
		this.citySpinner.startAnimation(menuAnim);
		
		this.citySpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int index, long arg3) {
				menuText.setText("你选择的是："+citys[index]);
				/* 将Spinner 显示*/
				adapterView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
				menuText.setText("请选择");
			}
			
		});
		
		this.citySpinner.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				/*运行animate*/
				view.startAnimation(menuAnim);
				/*将视图隐藏*/
				view.setVisibility(View.INVISIBLE);
				return false;
			}
		});
		
		this.citySpinner.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				
			}
		});
	}
}
