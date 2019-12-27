package com.anjoyo.jd.activity;

import java.util.ArrayList;
import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.SubCategoryAdapter;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.bean.SubCategoryBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubCategoryActivity extends BaseActivity {
	private ListView mListView1, mListView2;
	private ArrayList<SubCategoryBean> list;
	private TextView mTextView;
	JDParameters params;
	String category;
	SubCategoryAdapter secondAdapter;
	private String textName[] = { "服饰", "鞋靴", "数码", "办公", "个人化妆品", "图书", "母婴",
			"家用电器" };
	int imageViewClothes[] = { R.drawable.gallery_womencloth,
			R.drawable.gallery_mancloth };
	String categorynameClothes[] = { "女装", "男装" };
	String jutinameClothes[] = { "T恤/休闲装/打底裤", "参衣/大衣/皮衣" };
	int imageViewDigital[] = { R.drawable.camera, R.drawable.phone,
			R.drawable.peijian };
	String categorynameDigital[] = { "摄影摄像", "手机通讯", "手机配件" };
	String jutinameDigital[] = { "数码相机/单电/卡片机", "三星/苹果/华为", "苹果配件/数据线/电池" };
	static int flag;
	int mdarwable;
	private LayoutInflater inflater;
	View view;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subcategory_activity);
		flag = getIntent().getIntExtra("position", -1);
		inivview();
		
		mListView2addHeaderView(flag);
		mTextView.setText(textName[flag]);
		list = new ArrayList<SubCategoryBean>();
		secondAdapter = new SubCategoryAdapter(this, list);
		mListView2.setAdapter(secondAdapter);
		listView1FillData();
		listView2FillData(flag);
		listview1onclick();
		mListView2IntentToOther(flag);

	}

	private void mListView2addHeaderView(int flag3) {

		if (flag3 == 0) {
			mdarwable = R.drawable.coloth_shoe;
		} else if (flag3 == 2) {
			mdarwable = R.drawable.degital_promotion;
		}
		view.setBackgroundResource(mdarwable);
		mListView2.addHeaderView(view);
	}

	private void inivview() {
		inflater=LayoutInflater.from(this);
		view=inflater.inflate(R.layout.headview, null);
		mListView1 = (ListView) findViewById(R.id.category_activity_listview1);
		mListView2 = (ListView) findViewById(R.id.category_activity_listview2);
		mTextView = (TextView) findViewById(R.id.subcategoryname);
	}

	private void mListView2IntentToOther(int flag2) {
		if (flag2 == 0) {
			category = textName[flag2];
			Log.d("vivi", "category==" + category);
			mListView2.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {

					switch (position) {
					case 0:
						params = new JDParameters();
						params.add("sql", Constant.SQL_SELECT_GOODS1
								+ " where category='" + category + "')");
						getData(TAG_GOODS, Constant.URL_SELECT_GOODS, params,
								"GET");

						break;
					case 1:
						Intent intent11 = new Intent(SubCategoryActivity.this,
								TabHostContent.class);
						intent11.putExtra("sub_category", "女装");
						intent11.putExtra("TAG", "CategoryDetial");
						startActivity(intent11);

						break;

					case 2:
						Intent intent12 = new Intent(SubCategoryActivity.this,
								TabHostContent.class);
						intent12.putExtra("sub_category", "男装");
						intent12.putExtra("TAG", "CategoryDetial");
						startActivity(intent12);
						break;

					}

				}

			});
		} else if (flag2 == 2) {
			category = textName[flag2];
			mListView2.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					switch (position) {
					case 0:
						params = new JDParameters();
						params.add("sql", Constant.SQL_SELECT_GOODS1
								+ " where category='" + category + "')");
						getData(TAG_GOODS, Constant.URL_SELECT_GOODS, params,
								"GET");
						break;
					case 1:
						Intent intent20 = new Intent(SubCategoryActivity.this,
								TabHostContent.class);
						intent20.putExtra("sub_category", "摄影摄像");
						intent20.putExtra("TAG", "CategoryDetial");
						startActivity(intent20);
						break;

					case 2:
						Intent intent21 = new Intent(SubCategoryActivity.this,
								TabHostContent.class);
						intent21.putExtra("sub_category", "手机通讯");
						intent21.putExtra("TAG", "CategoryDetial");
						startActivity(intent21);
						break;
					case 3:
						Intent intent22 = new Intent(SubCategoryActivity.this,
								TabHostContent.class);
						intent22.putExtra("sub_category", "手机配件");
						intent22.putExtra("TAG", "CategoryDetial");
						startActivity(intent22);
						break;
					}
				}
			});
		}

	}

	private void listview1onclick() {
		mListView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Log.d("vivi", "position==" + position);
				listView2FillData(position);
				mTextView.setText(textName[position]);
				mListView2IntentToOther(position);

			}
		});
	}

	private void listView2FillData(int flag2) {
		// TODO Auto-generated method stub
		list.clear();
		Log.d("vivi", "mListView2.getCount()==" + mListView2.getCount());
		if (mListView2.getCount() > 0) {
			mListView2.removeHeaderView(view);
			mListView2.setAdapter(null);
		}

		if (flag2 == 0) {
			mdarwable = R.drawable.coloth_shoe;
			for (int i = 0; i < imageViewClothes.length; i++) {
				SubCategoryBean bean = new SubCategoryBean(imageViewClothes[i],
						categorynameClothes[i], jutinameClothes[i]);
				list.add(bean);
			}
		} else if (flag2 == 2) {
			mdarwable = R.drawable.degital_promotion;
			for (int i = 0; i < imageViewDigital.length; i++) {
				SubCategoryBean bean = new SubCategoryBean(imageViewDigital[i],
						categorynameDigital[i], jutinameDigital[i]);
				list.add(bean);

			}

		}
		view.setBackgroundResource(mdarwable);
		mListView2.addHeaderView(view);
		secondAdapter.adddata(list);
		mListView2.setAdapter(secondAdapter);
	}

	private void listView1FillData() {
		ArrayAdapter firstAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				textName);
		mListView1.setAdapter(firstAdapter);

	}

	@Override
	public void handleMsg(Message msg) {
		if (TAG_GOODS == msg.what) {
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			Log.d("vivi", "json==" + json);
			ArrayList<GoodsBean> bean = JSONUtil.getGoodsJson(json);
			Intent intent = new Intent(SubCategoryActivity.this,
					ListViewGridViewActivity.class);
			intent.putExtra("bean", bean);
			startActivity(intent);

		}
	}

}
