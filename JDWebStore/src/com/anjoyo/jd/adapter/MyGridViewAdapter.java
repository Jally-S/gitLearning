package com.anjoyo.jd.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjoyo.jd.R;
/**
 * 
 * @author Yuan_Junhua
 *
 */
public class MyGridViewAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<HashMap<Integer,String>> listMap;
	private LayoutInflater li;
public MyGridViewAdapter(Context context,ArrayList<HashMap<Integer,String>> listMap) {
	this.context=context;
	this.listMap=listMap;
	li=LayoutInflater.from(context);
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listMap.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listMap.get(position);
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
			convertView=li.inflate(R.layout.gridview_item, null);
			vh.img=(ImageView) convertView.findViewById(R.id.gridview_item_imgv);
			vh.tv=(TextView) convertView.findViewById(R.id.gridview_item_tv);
			convertView.setTag(vh);
		}else  vh=(ViewHolder) convertView.getTag();
		
		HashMap<Integer,String> map=listMap.get(position);
		Set<Integer> keySet=map.keySet();
		for (Integer integer : keySet) {
			vh.tv.setText(map.get(integer));
			vh.img.setImageResource(integer);
		}
		return convertView;
	}
	
	class ViewHolder{
		ImageView img;
		TextView tv;
		
	}

}
