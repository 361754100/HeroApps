package com.example.heroapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

/**
 * 网格视图
 * */
public class DemoGridViewActivity extends Activity {
	
	private GridView grid;
	private ImageView imgView;
	
	int[] imageIds = new int[]{
		R.drawable.g_011, R.drawable.g_012, R.drawable.g_013, R.drawable.g_014,
		R.drawable.g_021, R.drawable.g_022, R.drawable.g_023, R.drawable.g_024,
		R.drawable.g_031, R.drawable.g_032, R.drawable.g_033, R.drawable.g_034,
		R.drawable.g_041, R.drawable.g_042, R.drawable.g_043, R.drawable.g_044
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_gridview);
		//创建一个List对象，List对象的元素是Map
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		
		for(int i = 0; i< imageIds.length; i++){
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("image", imageIds[i]);
			
			listItems.add(listItem);
		}
		//获取显示图片imageView
		imgView = (ImageView)findViewById(R.id.imgv);
		//创建一个SimpleAdapter 
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, 
				listItems, 
				R.layout.cell ,
				new String[]{"image"}, 
				new int[]{R.id.cell_imgv});
		grid = (GridView)findViewById(R.id.gridv);
		//为GridView 设置 Adapter
		grid.setAdapter(simpleAdapter);
		grid.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				imgView.setImageResource(imageIds[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				imgView.setImageResource(imageIds[position]);
			}
			
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
