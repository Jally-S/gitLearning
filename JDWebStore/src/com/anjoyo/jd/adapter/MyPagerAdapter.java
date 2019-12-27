package com.anjoyo.jd.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
	public List<View> views;

	public MyPagerAdapter(List<View> views) {
		this.views = views;

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	/**
	 * 手动所重写的用来实例化的方法
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View v = views.get(position);
		container.addView(v);
		return v;
	}
	/**
	 * 手动所重写的用来做删除的方法
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(views.get(position));
	}
}

