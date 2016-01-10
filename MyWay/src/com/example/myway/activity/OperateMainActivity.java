package com.example.myway.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ClipData.Item;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myway.R;
import com.example.myway.adapter.SortApdapter;
import com.example.myway.model.ListViewSortModel;
import com.example.myway.plugin.CharacterParser;
import com.example.myway.plugin.PinYinsBar;
import com.example.myway.plugin.PinYinsBar.OnTouchingLetterChangedListener;
import com.example.myway.plugin.PinyinComparator;
import com.example.myway.service.MusicService;

public class OperateMainActivity extends Activity implements TabListener{
	
	private ListView listView;
	private PinYinsBar pinyinBar;
	private TextView dialog;
	private SortApdapter sortAdapter;
	private CharacterParser charParser;
	private List<ListViewSortModel> sourceList;
	private PinyinComparator pinYinComparator;
	
	private WebView webView = null;
	//音乐控制按钮
	private Button btnStart, btnStop, btnNext, btnLast;
	//开头字母选中时的中间浮动提示文本框
	private TextView txtInfo;
	//音乐进度条
	private SeekBar seekBar;
	//音乐控制服务（后台的）
	private MusicService musicService;
	private boolean autoChange, manulChange;
	private boolean isPause;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operate_grid_relative_index);
		
		listView = (ListView) findViewById(R.id.op_main_listView);
		seekBar = (SeekBar) findViewById(R.id.op_main_seekBar);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setCustomView(R.layout.action_bar_btn_layout);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
//		View item = getLayoutInflater().inflate(R.id.action_music, null);
//		actionBar.setCustomView(item, new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT, 
//	        		LayoutParams.WRAP_CONTENT));
//	        ActionBar.LayoutParams mP = (ActionBar.LayoutParams) item.getLayoutParams();
//	        mP.gravity = mP.gravity & ~Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.CENTER_HORIZONTAL;
//
//	        actionBar.setCustomView(item, mP);
		
		actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab1).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab2).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab3).setTabListener(this));
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				autoChange = false;
				manulChange = true;
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int progress = seekBar.getProgress();
				if(!autoChange && manulChange){
					int musicMax = musicService.player.getDuration();
					int seekBarMax = seekBar.getMax();
					
					int seekIndex = musicMax*progress/seekBarMax;
					
					musicService.player.seekTo(seekIndex);
					
					autoChange = true;
					manulChange = false;
				}
			}
		});
		
		musicService = new MusicService(seekBar);
		
		btnStart = (Button) findViewById(R.id.op_main_music_pause_btn);
		btnStart.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				musicService.start();
			}
		});
		
		btnLast = (Button) findViewById(R.id.op_main_music_last_btn);
		btnLast.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				musicService.prev();
			}
		});
		
		btnNext = (Button) findViewById(R.id.op_main_music_next_btn);
		btnNext.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				musicService.next();
			}
		});
		
		btnStop = (Button) findViewById(R.id.op_main_music_stop_btn);
		btnStop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				musicService.stop();
			}
		});
		
		charParser = CharacterParser.getInstance();
		pinYinComparator = new PinyinComparator();
		
		pinyinBar = (PinYinsBar) findViewById(R.id.pinYinsBar);
		dialog =(TextView) findViewById(R.id.sDialog);
		dialog.setTypeface(Typeface.DEFAULT_BOLD);
		pinyinBar.setTextView(dialog);
		
		//设置右侧触摸监听
		pinyinBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener(){
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = sortAdapter.getPositionForSection(s.charAt(0));
				if( position != -1 ){
					listView.setSelection(position);
				}
			}
		});
		
		setListViewAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.operate_main, menu);
		return true;
	}

	private void setListViewAdapter(){
		/***
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		for(String path: musicService.musicList){
			Map<String,Object> map = new HashMap<String,Object>();
			File file = new File(path);
			map.put("fileName", file.getName());
			data.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.
				simple_list_item_1, new String[]{"fileName"}, new int[]{android.R.id.text1});
		***/
		sourceList = new ArrayList<ListViewSortModel>();
		for(String path: musicService.musicList){
			File file = new File(path);
			
			ListViewSortModel sortModel = new ListViewSortModel();
			sortModel.setItemName(file.getName());
			sortModel.setItemPath(path);
			//汉字转换成拼音  
            String pinyin = charParser.getSelling(file.getName()); 
            String sortString = "";
            if( pinyin != null && !"".equals(pinyin)){
            	sortString = pinyin.substring(0, 1).toUpperCase();
            }
            // 正则表达式，判断首字母是否是英文字母  
            if(sortString.matches("[A-Z]")){  
                sortModel.setNamePinYin(sortString.toUpperCase());  
            } else {
                sortModel.setNamePinYin("*");  
            }
			
			sourceList.add(sortModel);
		}
		
		//根据a-z进行排序
		Collections.sort(sourceList, pinYinComparator);
		
		List<String> nMusicList = new ArrayList<String>();
		if(sourceList != null && sourceList.size() > 0 ){
			for(ListViewSortModel sortModel: sourceList){
				nMusicList.add(sortModel.getItemPath());
			}
			musicService.setMusicList(nMusicList);
		}
		
		sortAdapter = new SortApdapter(this, sourceList);
		
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View voew, int position,
					long id) {
				//这里要利用 sortAdapter.getItem(position)来获取当前position所对应的对象  
				Toast.makeText(getApplication(), ((ListViewSortModel)sortAdapter.getItem(position)).getItemName(), Toast.LENGTH_SHORT).show();
				musicService.setSongNum(position);
				musicService.start();
			}
		});
		listView.setAdapter(sortAdapter);
	}
	
	public String setPlayInfo(int position, int max){
		String info = "正在播放："+ musicService.songName;
		return info;
	}
	
	@Override
	public void onBackPressed() {
		//当用户按返回键时，则仅仅隐藏应用而不退出程序
		moveTaskToBack(true);
//		super.onBackPressed();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_exit:
				musicService.stop();
				super.finish();
				break;
	
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
