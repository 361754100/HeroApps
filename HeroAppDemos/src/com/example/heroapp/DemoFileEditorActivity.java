package com.example.heroapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DemoFileEditorActivity extends Activity implements OnClickListener {

	private EditText editor;
	private Button saveBtn;
	private String textInput;
	private OutputStream os;
	private TextView txtShow;
	private Button openBtn, clearBtn;
	private InputStream is;
	private byte[] b;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_file_edit_activity);
		
		editor = (EditText)findViewById(R.id.txtEditor);
		saveBtn = (Button)findViewById(R.id.txtSaveBtn);
		openBtn = (Button)findViewById(R.id.txtOpenBtn);
		clearBtn = (Button)findViewById(R.id.txtClearBtn);
		
		UIinit("main");
		Logic("main");
	}
	
	private void Logic(String logicStr) {
		if( "main".equals(logicStr) ){
			saveBtn.setOnClickListener(this);
		}else if( "open".equals(logicStr) ){
			openBtn.setOnClickListener(this);
			clearBtn.setOnClickListener(this);
		}
	}

	private void UIinit(String uiStr) {
		if( "main".equals(uiStr) ){
			editor = (EditText)findViewById(R.id.txtEditor);
			saveBtn = (Button)findViewById(R.id.txtSaveBtn);
		}else if( "open".equals(uiStr) ){
			txtShow = (TextView)findViewById(R.id.txtShow);
			openBtn = (Button)findViewById(R.id.txtOpenBtn);
			clearBtn = (Button)findViewById(R.id.txtClearBtn);
		}
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.txtSaveBtn:
				NoteDebug("保存文件");
				textInput = editor.getText().toString();
				try {
					os = this.openFileOutput("mydoc.txt", MODE_PRIVATE);
					os.write(textInput.getBytes());
				} catch (Exception e) {
					NoteDebug("保存文件失败："+e.getMessage());
				} finally{
					try {
						os.close();
					} catch (IOException e) {
						NoteDebug("文件关闭失败："+e.getMessage());
					}
				}
				editor.setText("");
				break;
			case R.id.txtOpenBtn:
				NoteDebug("打开文件");
				try {
					is = this.openFileInput("mydoc.txt");
					b = new byte[1024];
					try {
						int len = is.read(b);
						textInput = new String(b);
						setTitle("文件字数："+len);
						txtShow.setText(textInput);
					} catch (IOException e) {
						e.printStackTrace();
						NoteDebug("文件打开失败！");
					}
				} catch (FileNotFoundException e) {
					NoteDebug("找不到相关文件："+e.getMessage());
				} finally {
					try {
						is.close();
					} catch (Exception e2) {
						NoteDebug("文件关闭失败！");
					}
				}
				break;
			case R.id.txtClearBtn:
				NoteDebug("清空文件");
				txtShow.setText("");
				break;
		}
	}

	private void NoteDebug(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0,1,1,"打开");
		menu.add(0,2,2,"清空");
		menu.add(0,3,3,"退出");
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch( item.getItemId() ){
			case 1:
				/*显示demo_openfile.xml为主屏布局*/
				setLayoutShow(R.layout.demo_openfile);
				UIinit("open");
				Logic("open");
				NoteDebug("编辑文件Layout");
				break;
			case 2:
				/*显示demo_openfile.xml为主屏布局*/
				setLayoutShow(R.layout.demo_openfile);
				UIinit("open");
				Logic("open");
				NoteDebug("打开文件Layout");
				break;
			case 3:
				finish();
				NoteDebug("Bye Bye");
				break;
			default:
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 设置主屏的布局ID
	 * @param layoutID
	 */
	private void setLayoutShow(int layoutID) {
		/*设置当前主屏布局*/
		setContentView(layoutID);
	}
}
