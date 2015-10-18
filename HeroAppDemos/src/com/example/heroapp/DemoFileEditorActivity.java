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
				NoteDebug("�����ļ�");
				textInput = editor.getText().toString();
				try {
					os = this.openFileOutput("mydoc.txt", MODE_PRIVATE);
					os.write(textInput.getBytes());
				} catch (Exception e) {
					NoteDebug("�����ļ�ʧ�ܣ�"+e.getMessage());
				} finally{
					try {
						os.close();
					} catch (IOException e) {
						NoteDebug("�ļ��ر�ʧ�ܣ�"+e.getMessage());
					}
				}
				editor.setText("");
				break;
			case R.id.txtOpenBtn:
				NoteDebug("���ļ�");
				try {
					is = this.openFileInput("mydoc.txt");
					b = new byte[1024];
					try {
						int len = is.read(b);
						textInput = new String(b);
						setTitle("�ļ�������"+len);
						txtShow.setText(textInput);
					} catch (IOException e) {
						e.printStackTrace();
						NoteDebug("�ļ���ʧ�ܣ�");
					}
				} catch (FileNotFoundException e) {
					NoteDebug("�Ҳ�������ļ���"+e.getMessage());
				} finally {
					try {
						is.close();
					} catch (Exception e2) {
						NoteDebug("�ļ��ر�ʧ�ܣ�");
					}
				}
				break;
			case R.id.txtClearBtn:
				NoteDebug("����ļ�");
				txtShow.setText("");
				break;
		}
	}

	private void NoteDebug(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0,1,1,"��");
		menu.add(0,2,2,"���");
		menu.add(0,3,3,"�˳�");
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch( item.getItemId() ){
			case 1:
				/*��ʾdemo_openfile.xmlΪ��������*/
				setLayoutShow(R.layout.demo_openfile);
				UIinit("open");
				Logic("open");
				NoteDebug("�༭�ļ�Layout");
				break;
			case 2:
				/*��ʾdemo_openfile.xmlΪ��������*/
				setLayoutShow(R.layout.demo_openfile);
				UIinit("open");
				Logic("open");
				NoteDebug("���ļ�Layout");
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
	 * ���������Ĳ���ID
	 * @param layoutID
	 */
	private void setLayoutShow(int layoutID) {
		/*���õ�ǰ��������*/
		setContentView(layoutID);
	}
}
