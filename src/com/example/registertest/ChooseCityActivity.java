package com.example.registertest;

import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseCityActivity extends ExpandableListActivity {

	private String[] provinces=new String[]{"江西","江苏","浙江"};
	private String[][] cities=new String[][]{{"南昌","九江","赣州","吉安"},{"南京","苏州","无锡","扬州"},{"杭州","温州","台州","金华"}};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_city);
		
		ExpandableListAdapter adapter=new BaseExpandableListAdapter(){

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return cities[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				TextView textView=getTextView();
				textView.setText(getChild(groupPosition,childPosition).toString());
				return textView;
			}

			private TextView getTextView() {
				// TODO Auto-generated method stub
				AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
				TextView textView=new TextView(ChooseCityActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return cities[groupPosition].length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return provinces[groupPosition];
			}

			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return provinces.length;
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				LinearLayout ll=new LinearLayout(ChooseCityActivity.this);
				ll.setOrientation(LinearLayout.VERTICAL);
				ImageView logo=new ImageView(ChooseCityActivity.this);
				ll.addView(logo);
				TextView textView=getTextView();
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				return ll;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
		};
		setListAdapter(adapter);
		getExpandableListView().setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				
				Intent intent=getIntent();
				Bundle data=new Bundle();
				data.putString("city", cities[groupPosition][childPosition]);
				intent.putExtras(data);
				ChooseCityActivity.this.finish();
				return false;
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_city, menu);
		return true;
	}

}
