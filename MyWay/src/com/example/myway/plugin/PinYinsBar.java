package com.example.myway.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.myway.R;
/**
 * ListView �Ҳ��ƴ���б���
 * @author Mr Mo
 *
 */
public class PinYinsBar extends View {

	//�����¼�
	private OnTouchingLetterChangedListener onItemTouchListener = null;
	//26��Ӣ����ĸ
	private static String[] pinYins = {"A","B","C","D","E","F","G","H","I","J","K",
			"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","*"};
	
	private int choose = -1;
	
	private Paint paint = new Paint();
	private TextView mTextDialog = null;

	public void setTextView(TextView mTextDialog){
		this.mTextDialog = mTextDialog;
	}
	
	public PinYinsBar(Context context) {
		super(context);
	}
	
	public PinYinsBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public PinYinsBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		int height = getHeight();
		int width = getWidth();
		int singleHeight = height / pinYins.length;// ��ȡÿһ����ĸ�ĸ߶�
		
		for(int i = 0; i< pinYins.length; i++){
			paint.setColor(Color.parseColor("#FFFFFF"));
//			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(23);
			if(i == choose){
				paint.setColor(Color.parseColor("#080808"));
				paint.setFakeBoldText(true);
			}
			
			// x��������м�-�ַ�����ȵ�һ��.
			
			float xPos = width / 2- paint.measureText(pinYins[i]) / 2;
			float yPos = singleHeight * i+singleHeight;
			canvas.drawText(pinYins[i], xPos, yPos, paint);
			paint.reset();
		}
	}
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public boolean dispatchTouchEvent(MotionEvent event){
		final int action = event.getAction();
		final float y = event.getY();
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onItemTouchListener;
		final int c = (int) (y / getHeight() * pinYins.length);// ���y������ռ�ܸ߶ȵı���*b����ĳ��Ⱦ͵��ڵ��b�еĸ���.
		switch(action){
			case MotionEvent.ACTION_UP:
				setBackground(new ColorDrawable(Color.parseColor("#CCCCCC")));
//				setBackgroundColor(Color.CYAN);
//				setBackgroundDrawable(new ColorDrawable(0x00000000));
				choose = -1;//
				invalidate();
				if (mTextDialog != null) {
					mTextDialog.setVisibility(View.INVISIBLE);
				}
				break;
	
			default:
//				setBackgroundResource(R.drawable.sidebar_background);
				setBackground(new ColorDrawable(Color.parseColor("#C5C1AA")));
				if (oldChoose != c) {
					if (c >= 0 && c < pinYins.length) {
						if (listener != null) {
							listener.onTouchingLetterChanged(pinYins[c]);
						}
						if (mTextDialog != null) {
							mTextDialog.setText(pinYins[c]);
							mTextDialog.setVisibility(View.VISIBLE);
						}
						
						choose = c;
						invalidate();
					}
				}
	
				break;
			}
		return true;
	}
	
	/**
	 * ���⹫���ķ���
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onItemTouchListener = onTouchingLetterChangedListener;
	}

	/**
	 * �ӿ�
	 * 
	 * @author coder
	 * 
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}
}
