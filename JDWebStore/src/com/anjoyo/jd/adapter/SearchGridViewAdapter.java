package com.anjoyo.jd.adapter;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;

public class SearchGridViewAdapter extends BaseAdapter {
	List<GoodsBean> beans=new ArrayList<GoodsBean>();
	Context context;
	LayoutInflater inflater;

	public SearchGridViewAdapter(Context context,ArrayList<GoodsBean> beans) {
		this.beans=beans;
		this.context=context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return beans.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return beans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh=null;
		if(convertView==null){
			vh=new ViewHolder();
			convertView =inflater.inflate(R.layout.search_gridview_item, null);
			vh.goods_name=(TextView) convertView.findViewById(R.id.search_gridview_text);
			vh.goods_image=(ImageView) convertView.findViewById(R.id.search_gridview_image);
			convertView.setTag(vh);
		}else 
		{
			vh=(ViewHolder) convertView.getTag();
		}
		//convertView =inflater.inflate(R.layout.search_activity_item, null);
		vh.goods_name.setText("£¤£º"+beans.get(position).getNew_price());
		new LoadImage(context).loadDrawable(beans.get(position).getGoods_image(), vh.goods_image);
		
		return convertView;
	}
static class ViewHolder{
	TextView goods_name;
	ImageView goods_image;
}

public void addData(List<GoodsBean> beans) {
	this.beans=beans;
}

}
