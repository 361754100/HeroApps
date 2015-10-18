package com.example.heroapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class DemoNotificationMangeActivity extends Activity implements OnClickListener{
	
	private Button sendBtn;
	private Button exitBtn;
	private static final int NOTIFICATION_ID = 0x123;
	private NotificationManager ntManager;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_notification_activity);
		sendBtn = (Button)findViewById(R.id.sendBtn);
		exitBtn = (Button)findViewById(R.id.exitBtn);
		//获取系统的NotificationManager 服务
		ntManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		sendBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.sendBtn:
				Intent intent = new Intent();
				intent.setClass(DemoNotificationMangeActivity.this, DemoWebViewActivity.class);
				PendingIntent pi = PendingIntent.getActivity(DemoNotificationMangeActivity.this, 0, intent, 0);
	
				Notification notify = new Notification.Builder(this)
				//设置打开该通知，该通知自动消失
				.setAutoCancel(true)
				//设置显示在状态栏的通知提示信息
				.setTicker("有新消息")
				//设置通知的图标
				.setSmallIcon(R.drawable.clound)
				//是指通知内容
				.setContentText("恭喜你，您加薪了，工资增加了60%!")
				//设置使用系统默认的声音、默认LED灯
				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS)
				//设置通知的自定义声音
				.setSound(Uri.parse("android.resource://org.crazyit.ui/"+R.raw.demosound))
				.setWhen(System.currentTimeMillis())
				//设置通知将要启动程序的Intent
				.setContentIntent(pi)
				.build();
				//发送通知
				ntManager.notify(NOTIFICATION_ID, notify);
				break;
			case R.id.exitBtn:
				this.finish();
				break;
		}
	}
	
	//为删除通知的按钮的点击事件定义事件处理方法
	public void del(View v){
		ntManager.cancel(NOTIFICATION_ID);
	}
}
