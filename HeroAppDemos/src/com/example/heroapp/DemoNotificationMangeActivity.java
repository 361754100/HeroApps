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
		//��ȡϵͳ��NotificationManager ����
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
				//���ô򿪸�֪ͨ����֪ͨ�Զ���ʧ
				.setAutoCancel(true)
				//������ʾ��״̬����֪ͨ��ʾ��Ϣ
				.setTicker("������Ϣ")
				//����֪ͨ��ͼ��
				.setSmallIcon(R.drawable.clound)
				//��ָ֪ͨ����
				.setContentText("��ϲ�㣬����н�ˣ�����������60%!")
				//����ʹ��ϵͳĬ�ϵ�������Ĭ��LED��
				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS)
				//����֪ͨ���Զ�������
				.setSound(Uri.parse("android.resource://org.crazyit.ui/"+R.raw.demosound))
				.setWhen(System.currentTimeMillis())
				//����֪ͨ��Ҫ���������Intent
				.setContentIntent(pi)
				.build();
				//����֪ͨ
				ntManager.notify(NOTIFICATION_ID, notify);
				break;
			case R.id.exitBtn:
				this.finish();
				break;
		}
	}
	
	//Ϊɾ��֪ͨ�İ�ť�ĵ���¼������¼�������
	public void del(View v){
		ntManager.cancel(NOTIFICATION_ID);
	}
}
