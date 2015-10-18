package com.example.heroapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 可展开列表组件
 * */
public class DemoExpandableListViewActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_expandable);
		
		//创建一个BaseExpandableListApdater对象
		ExpandableListAdapter adapter = new BaseExpandableListAdapter(){
			
			int[] logos = new int[]{
					R.drawable.clound,
					R.drawable.wave,
					R.drawable.ellipse
			};
			
			private String[] armTypes = new String[]{"神族兵种","虫族兵种","人族兵种"};
			private String[][] arms = new String[][]{
					{"狂战士","龙骑士","黑暗圣堂"},
					{"小狗","刺蛇","飞龙","自爆飞机"},
					{"机枪兵","护士MM","幽灵"}
			};
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return arms[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			public TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
				TextView textView = new TextView(DemoExpandableListViewActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
					View converView, ViewGroup parent) {
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return arms[groupPosition].length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				return armTypes[groupPosition];
			}

			@Override
			public int getGroupCount() {
				return armTypes.length;
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
					ViewGroup parent) {
				LinearLayout ll = new LinearLayout(DemoExpandableListViewActivity.this);
				ll.setOrientation(0);
				ImageView logo = new ImageView(DemoExpandableListViewActivity.this);
				logo.setImageResource(logos[groupPosition]);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				return ll;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				return true;
			}
			
		};
		ExpandableListView expandListView = (ExpandableListView)findViewById(R.id.expand);
		expandListView.setAdapter(adapter);
	}
}
