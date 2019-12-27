package com.anjoyo.jd.util;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.anjoyo.jd.R;
import com.anjoyo.jd.activity.GoodDetailActivity;
import com.anjoyo.jd.activity.ListViewGridViewActivity;
import com.anjoyo.jd.adapter.ListViewAdapter;
import com.anjoyo.jd.adapter.ScrollViewAdater;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.view.HorzontialListView;

public class ViewPagerExceptHomeactivityUtil {
private View view;
private View headHorzontialLayout;
private Context context;
private ScrollViewAdater svAdapter;
private ListViewAdapter lvAdapter;
private HorzontialListView mHorzontialListView;
private ListView listView;

	public ViewPagerExceptHomeactivityUtil(
			Context context,
			View view,
			View headHorzontialLayout,
			HorzontialListView mHLV, 
			ScrollViewAdater svAdapter,
			ListViewAdapter lvAdapter,
			 ListView listView
			) {
		this.context=context;
		this.view=view;
		this.headHorzontialLayout=headHorzontialLayout;
		this.mHorzontialListView=mHLV;
		this.svAdapter=svAdapter;
		this.lvAdapter=lvAdapter;
		this.listView=listView;
		
	}
	
	public  void initeViewThreeContrls(final ArrayList<ArrayList<GoodsBean>> list) {
		// TODO Auto-generated method stub
		mHorzontialListView.setAdapter(svAdapter);
		mHorzontialListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				NotificationUtil.notificationUtil(context,
						"HorzontialListView" + position + "被点击");
				Intent intent = new Intent(context,
						GoodDetailActivity.class);
				intent.putExtra("goods", list.get(0).get(position));
				context.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 对下面的图片进行填充
		listView.addHeaderView(headHorzontialLayout);//把上面的横向ListView添加到listView头部上
		listView.setAdapter(lvAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				NotificationUtil.notificationUtil(context,
						"ListView 监听");
				Intent intent = new Intent(context,
						ListViewGridViewActivity.class);
				intent.putParcelableArrayListExtra("bean", list.get(1));
				context.startActivity(intent);
			}
		});
	}
	
}
