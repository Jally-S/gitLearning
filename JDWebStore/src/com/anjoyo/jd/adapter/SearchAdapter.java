package com.anjoyo.jd.adapter;

import java.util.ArrayList;
import java.util.List;

import com.anjoyo.jd.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter {
	List<String> beans=new ArrayList<String>();
	Context context;
	LayoutInflater inflater;

	public SearchAdapter(Context context,List<String> beans) {
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
			convertView =inflater.inflate(R.layout.search_activity_item, null);
			vh.tv=(TextView) convertView.findViewById(R.id.search_activity_item_text);
			convertView.setTag(vh);
		}else 
		{
			vh=(ViewHolder) convertView.getTag();
		}
		vh.tv.setText(beans.get(position));
		return convertView;
	}
class ViewHolder{
	TextView tv;
}

public void clear(){
	beans.clear();
}
public void addData(List<String> beans2) {
	beans=beans2;
}

}
