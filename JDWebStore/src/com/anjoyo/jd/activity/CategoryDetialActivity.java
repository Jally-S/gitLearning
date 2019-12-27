package com.anjoyo.jd.activity;

import java.util.ArrayList;
import java.util.HashMap;
import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.CategoryDetialAdapter;
import com.anjoyo.jd.adapter.GalleryAdapter;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;

import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryDetialActivity extends BaseActivity {
	private TextView mGood_Activity_TextView;
	private ListView mGood_Category_ListView;
	String sub_category;
	ArrayList<String> list;
	ArrayList<GoodsBean> gallerylist;
	CategoryDetialAdapter adapter;
	GalleryAdapter galleryAdapter;
	private Gallery mGallery;
	JDParameters params;
	LayoutInflater inflater;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_category_detail);
		initview();
		View view=inflater.inflate(R.layout.gallery, null);
		mGallery=(Gallery) view.findViewById(R.id.gallery);
		mGood_Category_ListView.addHeaderView(view);
		gallerylist = new ArrayList<GoodsBean>();
		galleryAdapter = new GalleryAdapter(this, gallerylist);
		mGallery.setAdapter(galleryAdapter);
		adapter = new CategoryDetialAdapter(this, list);
		Intent intent = getIntent();
		sub_category = intent.getStringExtra("sub_category");
		mGood_Activity_TextView.setText(sub_category);
		requestdata();
		mGood_Category_ListView.setAdapter(adapter);

	}

	private void requestdata() {
		params = new JDParameters();
		params.add("sub_category", sub_category);
		getData(TAG_SUB_CATEGORY, Constant.URL_GETCATEGORY, params, "GET");

		params = new JDParameters();
		params.add("sql", Constant.SQL_SELECT_GOODS + " where sub_category='"
				+ sub_category + "'");
		getData(TAG_GOODS, Constant.URL_SELECT_GOODS, params, "GET");
	}

	private void initview() {
		
		list = new ArrayList<String>();
		mGood_Category_ListView = (ListView) findViewById(R.id.good_category_detail_listview);
		mGood_Activity_TextView = (TextView) findViewById(R.id.good_category_detail_textview);
		inflater = LayoutInflater.from(this);
	}

	@Override
	public void handleMsg(Message msg) {
		if (msg.what == TAG_SUB_CATEGORY) {
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			Log.d("vivi", "json==" + json);
			HashMap hashMap = JSONUtil.getGoodsCategoryJson(json);
			if (hashMap.isEmpty()) {
				return;
			}
			final ArrayList<String> list = (ArrayList<String>) hashMap
					.get(sub_category);
			adapter.addbeans(list);
			adapter.notifyDataSetChanged();
			mGood_Category_ListView
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							Intent in = new Intent(CategoryDetialActivity.this,
									TabHostContent.class);
						
							String s_sub_category = list.get(position-1);
							in.putExtra("s_sub_category", s_sub_category);
							in.putExtra("sub_category", sub_category);
							in.putExtra("TAG", "SearchActivity");
							startActivity(in);

						}
					});

		} else if (msg.what == TAG_GOODS) {
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			Log.d("vivi", "json==" + json);
			final ArrayList<GoodsBean> beans = JSONUtil.getGoodsJson(json);
			galleryAdapter.addAll(beans);
			galleryAdapter.notifyDataSetChanged();
			mGallery.setSelection(2);
			mGallery.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					GoodsBean bean=beans.get(position);
					Intent intent=new Intent(CategoryDetialActivity.this,GoodDetailActivity.class);
					intent.putExtra("goods", bean);
					startActivity(intent);
					
				}
			});
			
		}
	}
}
