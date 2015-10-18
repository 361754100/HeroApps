package com.example.heroapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ÁÐ±íÊÓÍ¼
 * */
public class DemoListViewActivity extends Activity {
	
	private DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_listview_activity);
		
		ListView listView = (ListView)findViewById(R.id.listView1);
		final String[] names = new String[]{"ö¦×Ðè°1","ö¦×Ðè°2","ö¦×Ðè°3"};
		String[] descs = new String[]{"13Ëê","20Ëê","23Ëê"};
		int imgHeader = R.drawable.ic_listv;
		
		List<Map<String,Object>> itemList = new ArrayList<Map<String,Object>>();
		for(int i = 0; i<names.length; i++){
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("header", imgHeader);
			listItem.put("name", names[i]);
			listItem.put("desc", descs[i]);
			
			itemList.add(listItem);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, itemList,R.layout.list_item, new String[]{"name","header","desc"},new int[]{R.id.name,R.id.header,R.id.desc});
		
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				System.out.println(names[position]+"  id:"+id+"  ±»µã»÷ÁË");
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
