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
import android.widget.TextView;

public class GalleryAdapter extends BaseAdapter {
	ArrayList<GoodsBean> beans;
	LayoutInflater inflater;
	Context context;

	public GalleryAdapter(Context context, ArrayList<GoodsBean> gallerylist) {
		beans = gallerylist;
		this.context = context;
		inflater = LayoutInflater.from(context);
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
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.good_category_detail_item2,
					null);
			vh.mgallery_imageview = (ImageView) convertView
					.findViewById(R.id.gallery_imageview);
			vh.mgallery_name = (TextView) convertView
					.findViewById(R.id.gallery_name);
			vh.mgallery_price = (TextView) convertView
					.findViewById(R.id.gallery_price);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.mgallery_name.setText(beans.get(position).getGoods_name());
		vh.mgallery_price.setText("£¤£º" + beans.get(position).getNew_price());
		new LoadImage(context).loadDrawable(beans.get(position)
				.getGoods_image(), vh.mgallery_imageview);
		return convertView;
	}

	class ViewHolder {
		ImageView mgallery_imageview;
		TextView mgallery_name;
		TextView mgallery_price;
	}

	public void addAll(ArrayList<GoodsBean> beans2) {
		// TODO Auto-generated method stub
		beans = beans2;
	}

}
