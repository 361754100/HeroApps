package com.example.myway.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;

import com.example.myway.filter.MusicFilter;

public class MusicService {
	
	private static final File STORAGE_PATH = Environment.getExternalStorageDirectory();
	
	public List<String> musicList = null;
	
	public MediaPlayer player = null;
	
	private SeekBar seekBar;
	
	public int songNum = 0;
	
	public String songName = "";
	
	private MusicHandler musicHandler = null;
	
	public MusicService(SeekBar seekBar){
		this.seekBar = seekBar;

		musicList = new ArrayList<String>();
		player = new MediaPlayer();
		musicHandler = new MusicHandler();
		
		player.setOnPreparedListener(new OnPreparedListener(){
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
				MusicThread musicThread = new MusicThread();
				Thread thread = new Thread(musicThread);
				thread.start();
			}
		});
		
		String musicPath = STORAGE_PATH.getAbsolutePath()+"/Music";
		File musicFile = new File(musicPath);
		
		File[] musicFiles = musicFile.listFiles(new MusicFilter());		
		if(musicFiles.length > 0 ){
			for(File file:musicFiles){
				musicList.add(file.getAbsolutePath());
			}
			musicList.add("(End)");
		}
	}
	
	public int getSongNum() {
		return songNum;
	}

	public void setSongNum(int songNum) {
		this.songNum = songNum;
	}



	public void setPlayName(String filePath){
		File file = new File(filePath);
		String fileName = file.getName();
		int index =fileName.lastIndexOf("\\.");
		//songName = fileName.substring(0, index);
	}
	
	public void setMusicList(List<String> musicList) {
		this.musicList = musicList;
	}

	public void start(){
		
		try {
			player.reset();
			String dataSource = musicList.get(songNum);
			player.setDataSource(dataSource);
			setPlayName(dataSource);
			
			player.prepare();
//			player.start();
			
			player.setOnCompletionListener(new OnCompletionListener(){
				public void onCompletion(MediaPlayer player){
					next(); 
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void next(){
		songNum = songNum == musicList.size() -1?0:songNum+1;
		start();
	}
	
	public void prev(){
		songNum = songNum == 0? musicList.size() -1:songNum-1;
		start();
	}
	
	public void pause(){
		if(player.isPlaying()){
			player.pause();
		}else {
			player.start();
		}
	}
	
	public void stop(){
		if(player.isPlaying()){
			player.stop();
		}
	}
	
	class MusicHandler extends Handler{
		public MusicHandler(){}
		
		public void handleMessage(Message message){
//			if(autoChange){
				try {
					int position = player.getCurrentPosition();
					int mMax = player.getDuration();
					int sMax = seekBar.getMax();
					seekBar.setProgress(position*sMax/mMax);
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}else {
//				musicService.player.pause();
////				txtInfo.setText("≤•∑≈“—Õ£÷π");
//			}
		}
	}
	
	class MusicThread implements Runnable{

		public void run(){
			while(true){
				try {
					musicHandler.handleMessage(new Message());
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
 