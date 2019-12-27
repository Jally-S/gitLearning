package com.anjoyo.jd.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.anjoyo.jd.R;
import com.anjoyo.jd.net.LoadImage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private ArrayList<Integer> list;
	private LayoutInflater li;

	public ListViewAdapter(Context context, ArrayList<Integer> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		li = LayoutInflater.from(context);
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
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = li
					.inflate(
							R.layout.homeactivity_viewpageritemtwo_item_imageview,
							null);
			vh.img = (ImageView) convertView
					.findViewById(R.id.homeactivity_viewpageritemtwo_item_imageview);
			vh.tv = (TextView) convertView
					.findViewById(R.id.homeactivity_viewpageritemtwo_item_txview);
			convertView.setTag(vh);
		} else
			vh = (ViewHolder) convertView.getTag();

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String time = sd.format(new Date());
		vh.img.setImageResource(list.get(position));
		vh.tv.setText("½ØÖ¹ÈÕÆÚ:" + time);
		return convertView;
	}

	class ViewHolder {
		ImageView img;
		TextView tv;
	}

	public void fillData(ArrayList<Integer> list){
		this.list=list;

	}
}
