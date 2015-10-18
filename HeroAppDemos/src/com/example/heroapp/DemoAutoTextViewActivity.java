package com.example.heroapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

/**
 * 自动完成文本框
 * */
public class DemoAutoTextViewActivity extends Activity {
	
	private DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private AutoCompleteTextView auto = null;
	private MultiAutoCompleteTextView mauto = null;
	private String[] books = {"Book1","Book2","Book3"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_autocomplete);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,  android.R.layout.simple_dropdown_item_1line, books);
		auto = (AutoCompleteTextView)findViewById(R.id.auto);
		auto.setAdapter(aa);
		
		mauto = (MultiAutoCompleteTextView)findViewById(R.id.mauto);
		mauto.setAdapter(aa);
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
