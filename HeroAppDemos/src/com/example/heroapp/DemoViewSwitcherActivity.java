package com.example.heroapp;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

public class DemoViewSwitcherActivity extends Activity {
	
	public static final int NUMBER_PER_SCREEN = 12;
	public static class DataItem{
		public String dataName;
		public Drawable drawable;
	}
	
	private ArrayList<DataItem> items = new ArrayList<DataItem>();
	private int screenNo = 0;
	private int screenCount;
	ViewSwitcher switcher;
	LayoutInflater inflater;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_viewswitcher_layout);
		inflater = LayoutInflater.from(DemoViewSwitcherActivity.this);
		for(int i = 0; i<10; i++){
			String label = ""+i;
			Drawable drawable = getResources().getDrawable(R.drawable.atom);
			DataItem item = new DataItem();
			item.dataName = label;
			item.drawable = drawable;
			items.add(item);
		}
		screenCount = items.size()%NUMBER_PER_SCREEN == 0 ?
				items.size()/NUMBER_PER_SCREEN:
				items.size()/NUMBER_PER_SCREEN + 1;
		switcher = (ViewSwitcher)findViewById(R.id.viewSwitcher);
		switcher.setFactory(new ViewFactory(){
			public View makeView(){
				return inflater.inflate(R.layout.demo_slidelistview, null);
			}
		});
		next(null);
	}
	
	public void next(View v){
		if(screenNo < screenCount - 1){
			screenNo++;
			switcher.setInAnimation(this, R.anim.slide_in_right);
			switcher.setOutAnimation(this, R.anim.slide_out_left);
			((GridView)switcher.findViewById(R.id.slideGridView)).setAdapter(adapter);
			switcher.showNext();
		}
	}
	
	public void prev(View v){
		if(screenNo > 0 ){
			screenNo--;
			switcher.setInAnimation(this, android.R.anim.slide_in_left);
			switcher.setInAnimation(this, android.R.anim.slide_out_right);
			((GridView)switcher.findViewById(R.id.slideGridView)).setAdapter(adapter);
			switcher.showPrevious();
		}
	}
	
	private BaseAdapter adapter = new BaseAdapter(){
		public int getCount(){
			if(screenNo == screenCount - 1 && items.size() % NUMBER_PER_SCREEN != 0){
				return items.size() % NUMBER_PER_SCREEN;
			}
			return NUMBER_PER_SCREEN;
		}
		
		public DataItem getItem(int position){
			return items.get(screenNo*NUMBER_PER_SCREEN+position);
		}
		
		public long getItemId(int position){
			return position;
		}
		
		public View getView(int position, View convertView, ViewGroup parent){
			View view = convertView;
			if(convertView == null){
				view = inflater.inflate(R.layout.labelicon, null);
			}
			ImageView imageView = (ImageView)view.findViewById(R.id.imageview);
			imageView.setImageDrawable(getItem(position).drawable);
			TextView textView = (TextView)view.findViewById(R.id.textview);
			textView.setText(getItem(position).dataName);
			return view;
		}
	};
};
