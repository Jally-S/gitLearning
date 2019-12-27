package com.anjoyo.jd.adapter;

import java.util.ArrayList;

import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.GoodsBean;
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

public class ScrollViewAdater extends BaseAdapter implements ListAdapter {
	private Context context;
	private ArrayList<GoodsBean> list;
	private LayoutInflater li;
public ScrollViewAdater(Context context,ArrayList<GoodsBean> list) {
	// TODO Auto-generated constructor stub
	this.context=context;
	this.list=list;
	li=LayoutInflater.from(context);
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("static-access")
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder vh=null;
		if(convertView==null){
			vh=new ViewHolder();
			convertView=li.inflate(R.layout.viewpagerone_scrollveiw_four, null);
			vh.img=(ImageView) convertView.findViewById(R.id.homeactivity_listview_item_four_HorizontalScrollView_img);
			vh.tv=(TextView) convertView.findViewById(R.id.homeactivity_listview_item_four_HorizontalScrollView_tv);
			convertView.setTag(vh);
		}else vh=(ViewHolder) convertView.getTag();
		
		GoodsBean bean=list.get(position);
		new LoadImage(context).loadDrawable(bean.getGoods_image(), vh.img);
			vh.tv.setText("´ÙÏú¼Û  £¤£º"+bean.getNew_price()+"Ôª");
		return convertView;
	}
	class ViewHolder{
		ImageView img;
		TextView tv;
	}
public void fillData(ArrayList<GoodsBean> list){
	this.list=list;
}
}
