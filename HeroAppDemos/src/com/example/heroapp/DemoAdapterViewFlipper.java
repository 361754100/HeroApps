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
			
			//�÷������ص�View������ÿ���б���
			public View getView(int position, View convertView, ViewGroup parent){
				//����һ��ImageView
				ImageView imageView = new ImageView(DemoAdapterViewFlipper.this);
				imageView.setImageResource(imageIds[position]);
				//����ImageView����������
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				//ΪimageView ���ò��ֲ���
				imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				return imageView;
			}
		};
		flipper.setAdapter(adapter);
	}
	
	public void prev(View source){
		//��ʾ��һ�����
		flipper.showPrevious();
		//ֹͣ�Զ�����
		flipper.stopFlipping();
	}
	
	public void next(View source){
		//��ʾ��һ�����
		flipper.showNext();
		//ֹͣ�Զ�����
		flipper.stopFlipping();
	}
	
	public void auto(View source){
		//��ʼ�Զ�����
		flipper.startFlipping();
	}
	
}
