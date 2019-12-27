package com.anjoyo.jd.adapter;

import java.util.ArrayList;
import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.net.LoadImage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListGridViewAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<GoodsBean> mGridBean;
	private LayoutInflater li;
	public ListGridViewAdapter(Context context,ArrayList<GoodsBean> mGridBean) {
		this.context=context;
		this.mGridBean=mGridBean;
		li=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mGridBean.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mGridBean.get(position);
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
			convertView=li.inflate(R.layout.listview_gridviewitem, null);
			vh.img=(ImageView) convertView.findViewById(R.id.homeactivity_listview_item_five_HorizontalScrollView_img);
			vh.goodsName=(TextView) convertView.findViewById(R.id.homeactivity_listview_item_five_HorizontalScrollView_tv);
			vh.goodsPrice=(TextView) convertView.findViewById(R.id.homeactivity_listview_item_five_HorizontalScrollView_tv2);
			convertView.setTag(vh);
		}else vh=(ViewHolder) convertView.getTag();
		
		GoodsBean bean=mGridBean.get(position);
		new LoadImage(context).loadDrawable(bean.getGoods_image(), vh.img);
		vh.goodsName.setText(bean.getGoods_name());
		vh.goodsPrice.setText("¼Û¸ñ £¤:"+bean.getNew_price()+"Ôª");
		
		
		return convertView;
	}
	class ViewHolder{
		ImageView img;
		TextView goodsName;
		TextView goodsPrice;
	}
	
	public void fillData(ArrayList<GoodsBean> mGridBean){
		this.mGridBean=mGridBean;
	}

}
