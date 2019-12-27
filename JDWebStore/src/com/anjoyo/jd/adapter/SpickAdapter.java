package com.anjoyo.jd.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.net.LoadImage;
import com.anjoyo.jd.util.CountdownUtil;
import com.anjoyo.jd.util.NotificationUtil;

public class SpickAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<GoodsBean> goodsBean;
	private LayoutInflater li;
  public SpickAdapter(Context context,ArrayList<GoodsBean> goodsBean) {
	// TODO Auto-generated constructor stub
	  this.context=context;
	  this.goodsBean=goodsBean;
	  li=LayoutInflater.from(context);
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsBean.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return goodsBean.get(position);
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
			convertView=li.inflate(R.layout.spicklistview_item, null);
			vh.imgPic=(ImageView) convertView.findViewById(R.id.spickitem_imgview);
			vh.timeTv=(TextView) convertView.findViewById(R.id.spickitem_tvview1);
			vh.goodsDetail=(TextView) convertView.findViewById(R.id.spickitem_tvview2);
			vh.priceTv=(TextView) convertView.findViewById(R.id.spickitem_tvview3);
			vh.newPriceTv=(TextView) convertView.findViewById(R.id.spickitem_tvview5);
			vh.spickBt=(Button) convertView.findViewById(R.id.spickitem_Button);		
			convertView.setTag(vh);
		}else vh=(ViewHolder) convertView.getTag();
		
		GoodsBean beans=goodsBean.get(position);
		new LoadImage(context).loadDrawable(beans.getGoods_image(), vh.imgPic);
		//vh.timeTv.setText(beans.getGoodsBadline());
		new CountdownUtil().countdown(vh.timeTv);
		vh.goodsDetail.setText(beans.getGoods_name());
		vh.priceTv.setText("£§£∫"+beans.getOld_price());
		vh.newPriceTv.setText("£§£∫"+beans.getNew_price());
		vh.spickBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NotificationUtil.notificationUtil(context, "spick_item_¡¢º¥√Î…±");
			}
		});
		
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView imgPic;
		Button spickBt;
		TextView timeTv;
		TextView priceTv;
		TextView newPriceTv;
		TextView goodsDetail;
		
	}

public void fillSpickData(ArrayList<GoodsBean> goodsBean){
	this.goodsBean=goodsBean;
}
}
