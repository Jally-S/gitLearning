package com.anjoyo.jd.adapter;

import java.util.ArrayList;

import com.anjoyo.jd.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryDetialAdapter extends BaseAdapter {
	ArrayList<String> list;
	LayoutInflater inflater;
	public CategoryDetialAdapter(Context context,
			ArrayList<String> list) {
	this.list=list;
	inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
	
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoler vh=null;
		if(convertView==null){
			vh=new ViewHoler();
			convertView=inflater.inflate(R.layout.good_category_detail_item, null);
			vh.tv=(TextView) convertView.findViewById(R.id.good_detail_item_textview);
			vh.iv=(ImageView) convertView.findViewById(R.id.good_detail_item_imageview);
			convertView.setTag(vh);
			
		}else{
			vh=(ViewHoler) convertView.getTag();
		}
		String goodname=list.get(position);
		vh.tv.setText(goodname);
		
		return convertView;
		
	}
	class ViewHoler{
		TextView tv;
		ImageView iv;
	}

	public void addbeans(ArrayList<String> list1) {
		// TODO Auto-generated method stub
		list=list1;
	}

}
