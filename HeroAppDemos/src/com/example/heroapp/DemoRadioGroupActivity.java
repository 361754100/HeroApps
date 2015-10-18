package com.example.heroapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DemoRadioGroupActivity extends Activity {
	
	private RadioGroup radioGroup;
	private RadioButton radioBoy, radioGirl;
	private AlertDialog.Builder dialogBuilder;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_radiogroup);
		this.radioBoy = (RadioButton)findViewById(R.id.radioBoy);
		this.radioGirl = (RadioButton)findViewById(R.id.radioGirl);
		this.radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		
		this.dialogBuilder = new AlertDialog.Builder(this);
		
		this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int index) {
				if(radioBoy.isChecked()){
//					Toast.makeText(DemoRadioGroupActivity.this, "你选择的是【Boy】 index->"+index, Toast.LENGTH_LONG).show();
					dialogBuilder.setIcon(R.drawable.atom);
					dialogBuilder.setTitle("单选提示：");
					dialogBuilder.setMessage( "你选择的是【Boy】 index->"+index);
				}else {
//					Toast.makeText(DemoRadioGroupActivity.this, "你选择的是【Girl】 index->"+index, Toast.LENGTH_LONG).show();
					dialogBuilder.setIcon(R.drawable.clound);
					dialogBuilder.setTitle("单选提示：");
					dialogBuilder.setMessage( "你选择的是【Girl】 index->"+index);
				}
				dialogBuilder.setPositiveButton("确定", new OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						finish();
					}
				});
				dialogBuilder.show();
			}
		});
	}
}
