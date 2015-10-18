package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class DemoAdapterViewFlipper extends Activity {
	
	int[] imageIds = new int[]{
		R.drawable.atom, R.drawable.clound,
		R.drawable.ellipse, R.drawable.flag
	};
	
	AdapterViewFlipper flipper;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_adapter_flipper);
		flipper = (AdapterViewFlipper)findViewById(R.id.flipper);
		
		BaseAdapter adapter = new BaseAdapter(){
			public int getCount(){
				return imageIds.length;
			}
			public Object getItem(int position){
				return position;
			}
			
			public long getItemId(int position){
				return position;
			}
			
			//该方法返回的View代表了每个列表项
			public View getView(int position, View convertView, ViewGroup parent){
				//创建一个ImageView
				ImageView imageView = new ImageView(DemoAdapterViewFlipper.this);
				imageView.setImageResource(imageIds[position]);
				//设置ImageView的缩放类型
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				//为imageView 设置布局参数
				imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				return imageView;
			}
		};
		flipper.setAdapter(adapter);
	}
	
	public void prev(View source){
		//显示上一个组件
		flipper.showPrevious();
		//停止自动播放
		flipper.stopFlipping();
	}
	
	public void next(View source){
		//显示下一个组件
		flipper.showNext();
		//停止自动播放
		flipper.stopFlipping();
	}
	
	public void auto(View source){
		//开始自动播放
		flipper.startFlipping();
	}
	
}
