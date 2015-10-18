package com.example.heroapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DemoSpinnerActivity extends Activity implements OnClickListener, OnItemSelectedListener{
	private TextView textView;
	private EditText editText;
	private Button addBtn;
	private Button delBtn;
	private Spinner selector;
	private ArrayAdapter<String> arrayAdapter;
	private String[] citys;
	private String addString;
	private List<String> cityList;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_menu_spinner_mod);
		
		textView = (TextView)findViewById(R.id.choiceText);
		editText = (EditText)findViewById(R.id.editInput);
		
		addBtn = (Button)findViewById(R.id.editAdd);
		delBtn = (Button)findViewById(R.id.editDel);
		
		selector = (Spinner)findViewById(R.id.spinner_select);

		citys = new String[]{"中山市","广州市","东莞市","珠海市"};
		cityList = new ArrayList<String>();
		
		for(String city:citys){
			cityList.add(city);
		}
		
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		selector.setAdapter(arrayAdapter);
		
		textView.setText(arrayAdapter.getItem(0));
		selector.setSelection(0);
		
		addBtn.setOnClickListener(this);
		delBtn.setOnClickListener(this);
		selector.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case  R.id.editAdd:
				Toast.makeText(this, "添加城市信息", Toast.LENGTH_LONG).show();
				addString = editText.getText().toString();
				for(int i = 0; i< arrayAdapter.getCount(); i++){
					if(addString.equals(arrayAdapter.getItem(i))){
						return;
					}
				}
				if(!"".equals(addString)){
					arrayAdapter.add(addString);
					int position = arrayAdapter.getPosition(addString);
					selector.setSelection(position);
					editText.setText("");
				}
				break;
			case R.id.editDel:
				if(selector.getSelectedItem() != null){
					arrayAdapter.remove(selector.getSelectedItem().toString());
					editText.setText("");
					if(arrayAdapter.getCount() == 0){
						Toast.makeText(this, "数据已清空", Toast.LENGTH_LONG).show();
						editText.setText("");
					}
				}
				break;
			default: break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int index,
			long arg3) {
		textView.setText(arrayAdapter.getItem(index));
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add("Exit");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		finish();
		return super.onOptionsItemSelected(item);
	}
}
