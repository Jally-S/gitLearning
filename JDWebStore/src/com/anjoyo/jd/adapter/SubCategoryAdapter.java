package com.anjoyo.jd.adapter;

import java.util.ArrayList;
import java.util.List;

import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.GalleryAdapter.ViewHolder;
import com.anjoyo.jd.bean.SubCategoryBean;
import com.anjoyo.jd.net.LoadImage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SubCategoryAdapter extends BaseAdapter {

private List<SubCategoryBean> list;
private LayoutInflater inflater;

	public SubCategoryAdapter(Context context,
			List<SubCategoryBean> list) {
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
		ViewHolder vh=null;
		if (convertView == null) {
		vh = new ViewHolder();
		convertView = inflater.inflate(R.layout.rightmenu_item,
				null);
		vh.iv = (ImageView) convertView
				.findViewById(R.id.rightmenu_item_imageview);
		vh.tv1 = (TextView) convertView
				.findViewById(R.id.rightmenu_item_textview1);
		vh.tv2 = (TextView) convertView
				.findViewById(R.id.rightmenu_item_textview2);
		convertView.setTag(vh);
	} else {
		vh = (ViewHolder) convertView.getTag();
	}
	vh.tv1.setText(list.get(position).getmSubCategoryGoodname());
	vh.tv2.setText( list.get(position).getmSubCategoryDeatil());
	vh.iv.setImageResource(list.get(position).getmSubCategoryGoodImageView());
	return convertView;
	}
	class ViewHolder{
		ImageView iv;
		TextView tv1;
		TextView tv2;
	}
	public void adddata(ArrayList<SubCategoryBean> list2) {
		// TODO Auto-generated method stub
		list=list2;
	}

	public void clear() {
		// TODO Auto-generated method stub
		list=null;
	}

}
