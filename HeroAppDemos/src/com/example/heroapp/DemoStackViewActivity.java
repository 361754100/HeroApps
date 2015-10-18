package com.example.heroapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;

public class DemoStackViewActivity extends Activity {
	
	StackView stackView;
	int[] imageIds = new int[]{
		R.drawable.atom, R.drawable.clound,
		R.drawable.ellipse, R.drawable.flag	
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_stackview);
		stackView = (StackView)findViewById(R.id.stackView);
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		for(int i = 0; i<imageIds.length; i++){
			Map<String,Object> listItem= new HashMap<String,Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.demo_stackview, 
				new String[]{"images"},new int[]{R.id.imgv});
		stackView.setAdapter(simpleAdapter);
		
	}
	
	public void prev(View view){
		stackView.showPrevious();
	}
	public void next(View view){
		stackView.showNext();
	}
}
