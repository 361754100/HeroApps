package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.heroapp.util.HttpServerUtil;

public class DemoWebViewActivity extends Activity implements OnClickListener{
	
	private WebView webView;
	private Button hisBtn;
	private Button forwardBtn;
	private Handler jsHandler = new Handler();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_webview_layout);
		
		hisBtn = (Button)findViewById(R.id.hisBtn);
		hisBtn.setOnClickListener(this);
		
		forwardBtn = (Button)findViewById(R.id.forwardBtn);
		forwardBtn.setOnClickListener(this);
		
		webView = (WebView)findViewById(R.id.webView);
		//避免用系统自带的浏览器打开页面
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView webView, String url){
				return false;
			}
			
			public void onPageFinished(WebView webView, String url){
				super.onPageFinished(webView, url);
				Toast.makeText(DemoWebViewActivity.this, "页面加载完毕！", Toast.LENGTH_LONG).show();
			}
		});
		//允许调用javascript代码
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient(){
			public boolean onJsAlert(WebView webView, String url, String message, JsResult result){
				return super.onJsAlert(webView, url, message, result);
			}
		});
		
		webView.addJavascriptInterface(new Object(){
			@JavascriptInterface
			public void clickOnAndroid(){
				jsHandler.post(new Runnable(){
					@Override
					public void run() {
						exit();
					}
				});
			}
		}, "appExit");
		
		webView.loadUrl("http://"+HttpServerUtil.serverIp+":"+HttpServerUtil.serverPort+"/upc/index.jsp");
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.hisBtn:
				webView.goBack();
				break;
			case R.id.forwardBtn:
				webView.goForward();
				break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
		if(webView.canGoBack() && keyCode == keyEvent.KEYCODE_BACK){
			webView.goBack();
			return true;
		}
		
		if(!webView.canGoBack()){
			webView.loadUrl("javascript:exit()");
			return true;
		}
		return false;
	}
	
	public void exit(){
		Toast.makeText(this, "正在退出！", Toast.LENGTH_LONG).show();
		this.finish();
	}
}
