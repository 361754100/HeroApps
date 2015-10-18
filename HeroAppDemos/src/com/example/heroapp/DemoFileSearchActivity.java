package com.example.heroapp;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DemoFileSearchActivity extends Activity implements OnClickListener {

	private File file;
	private String path;
	private String info;
	private String theKey_fromInput;
	private TextView resultView;
	private EditText searchKey;
	private Button searchBtn;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_sdcard_activity);
		
		resultView = (TextView)findViewById(R.id.resultView);
		searchKey = (EditText)findViewById(R.id.inputText);
		searchBtn = (Button)findViewById(R.id.searchBtn);
		
		searchBtn.setOnClickListener(this);
		
		file = android.os.Environment.getExternalStorageDirectory();
		/**获取sdCard的路径**/
		info = getString(R.string.sdCardPath);
	}
	
	@Override
	public void onClick(View view) {
		path = "";
		resultView.setText("");
		theKey_fromInput = searchKey.getText().toString();
		BrowserFile(file);
	}

	private void BrowserFile(File openFile) {
		if("".equals(theKey_fromInput)){
			Toast.makeText(this, "请输入搜索关键字", Toast.LENGTH_LONG).show();
		}else {
			toSearchFile(openFile);
			if( "".equals(resultView.getText()) ){
				Toast.makeText(this, "", Toast.LENGTH_LONG);
			}
		}
	}

	private void toSearchFile(File searchFile) {
		/*定义一个File文件数组，用来存放/sdcard目录下的文件或文件夹*/
		if(searchFile == null ){
			Toast.makeText(this, getString(R.string.noFound), Toast.LENGTH_LONG).show();
			return;
		}
		
		boolean isDirect = searchFile.isDirectory();
		File[] files = searchFile.listFiles();
		if(files == null ){
			Toast.makeText(this, getString(R.string.noFound), Toast.LENGTH_LONG).show();
			return;
		}
		for(File temp:files){
			if(temp.isDirectory()){
				toSearchFile(temp);
			}else {
				if(temp.getName().indexOf(theKey_fromInput) != -1){
					path += temp.getPath();
					resultView.setText(info+path);
				}else {
					Toast.makeText(this, getString(R.string.noFound), Toast.LENGTH_LONG).show();
				}
			}
		}
	}

}
