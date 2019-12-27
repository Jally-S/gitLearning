package com.anjoyo.jd.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.SearchAdapter;
import com.anjoyo.jd.adapter.SearchAdapterDetails;
import com.anjoyo.jd.adapter.SearchGridViewAdapter;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.db.DBHelper;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;
import com.anjoyo.jd.view.JDListView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends BaseActivity implements OnClickListener {
	private ImageView iv1, iv2, iv3, iv4, search_mic, loadimage, switch_image;
	private ImageView ivs[]={iv1, iv2, iv3, iv4};
	private ArrayList<View> views;
	private TextView tv1, tv2, tv3, tv4;
	private TextView[] tvs={ tv1, tv2, tv3, tv4};
	private ImageButton search_button1;
	private View s_title, s_normal, s_linear;
	private EditText mSearch_EditText;
	private JDListView mSearch_ListView;
	private GridView mSearch_GridView;
	private Button search_clear, search_button2;
	private ArrayList<String> beans;
	private SearchAdapter adapter;
	private SearchAdapterDetails adapterDetails;
	private SearchGridViewAdapter adapterGridView;
	private DBHelper dbHelper;
	private ArrayList<GoodsBean> goodsBeans;
	private JDParameters params;
	private boolean bo = false;
	private AnimationDrawable anim;
	private boolean tagMic=false;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		dbHelper = new DBHelper(this);
		beans = new ArrayList<String>();
		adapter = new SearchAdapter(this, beans);
		goodsBeans = new ArrayList<GoodsBean>();
		adapterDetails = new SearchAdapterDetails(this, goodsBeans);
		adapterGridView = new SearchGridViewAdapter(this, goodsBeans);
		initview();
		fillListView();
		eventIsClicked();

	}

	public void initview() {
		mSearch_ListView = (JDListView) findViewById(R.id.search_listview);
		mSearch_GridView = (GridView) findViewById(R.id.s_gridview);
		mSearch_GridView.setAdapter(adapterGridView);
		s_title = findViewById(R.id.s_title);
		s_normal = findViewById(R.id.s_normal);
		s_linear = findViewById(R.id.s_linear);
		search_mic = (ImageView) findViewById(R.id.search_mic);
		switch_image = (ImageView) findViewById(R.id.s_switch);
		ivs[0]= (ImageView) findViewById(R.id.s_iv1);
		ivs[1] = (ImageView) findViewById(R.id.s_iv2);
		ivs[2] = (ImageView) findViewById(R.id.s_iv3);
		ivs[3] = (ImageView) findViewById(R.id.s_iv4);
		loadimage = (ImageView) findViewById(R.id.loadimage);
		loadimage.setBackgroundResource(R.drawable.animloading);
		anim = (AnimationDrawable) loadimage.getBackground();
		tvs[0] = (TextView) findViewById(R.id.s_tv1);
		tvs[1]= (TextView) findViewById(R.id.s_tv2);
		tvs[2] = (TextView) findViewById(R.id.s_tv3);
		tvs[3]= (TextView) findViewById(R.id.s_tv4);	
		search_button1 = (ImageButton) findViewById(R.id.search_button1);
		mSearch_EditText = (EditText) findViewById(R.id.search_edittext);
		search_clear = (Button) findViewById(R.id.search_clear);
		search_button2 = (Button) findViewById(R.id.search_button2);
		views = new ArrayList<View>();
		views.add(s_title);
		views.add(s_normal);
		views.add(search_clear);
		mSearch_ListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (search_clear.isShown()||tagMic) {
					setViewVisibility(4);
					clearDate();
					search(beans.get(position).toString());
					tagMic=false;
				} else if (goodsBeans.size() > 0) {
					// Toast.makeText(SearchActivity.this,
					// goodsBeans.get(position).toString(), 500).show();
					Intent intent = new Intent(SearchActivity.this,
							GoodDetailActivity.class);
					intent.putExtra("goods", goodsBeans.get(position));
					startActivity(intent);
				}
			}
		});
		mSearch_GridView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			
					Intent intent = new Intent(SearchActivity.this,
							GoodDetailActivity.class);
					intent.putExtra("goods", goodsBeans.get(position));
					startActivity(intent);
				
			}
		});

	}

	public void eventIsClicked() {

		search_button1.setOnClickListener(this);
		search_button2.setOnClickListener(this);
		search_clear.setOnClickListener(this);
		tvs[0].setOnClickListener(this);
		tvs[1].setOnClickListener(this);
		tvs[2].setOnClickListener(this);
		tvs[3].setOnClickListener(this);
		search_mic.setOnClickListener(this);
		switch_image.setOnClickListener(this);

	}

	public void fillListView() {
		Intent intent = getIntent();
		String category = intent.getStringExtra("category");
		String sub_category = intent.getStringExtra("sub_category");
		String s_sub_category = intent.getStringExtra("s_sub_category");
		String mic = intent.getStringExtra("mic");
		if (sub_category != null && s_sub_category != null) {
			mSearch_ListView.setAdapter(adapterDetails);
			setViewVisibility(4);
			if ("女装".equals(sub_category)) {
				s_sub_category = "女士" + s_sub_category;
			} else if ("男装".equals(sub_category)) {
				s_sub_category = "男士" + s_sub_category;
			} else if ("童装".equals(sub_category)) {
				s_sub_category = "儿童" + s_sub_category;
			}
			params = new JDParameters();
			params.add("sql", Constant.SQL_SELECT_GOODS
					+ " where sub_category='" + sub_category
					+ "' and t1.s_sub_category='" + s_sub_category + "'");
			getData(TAG_GOODS, Constant.URL_SELECT_GOODS, params, "GET");
			loadimage.setVisibility(View.VISIBLE);
			mHandler.sendEmptyMessageDelayed(22, 500);
		} else if (category != null) {
			mSearch_ListView.setAdapter(adapterDetails);
			params = new JDParameters();
			params.add("sql", Constant.SQL_SELECT_GOODS1 + " where category='"
					+ category + "')");
			getData(TAG_GOODS, Constant.URL_SELECT_GOODS, params, "GET");
			setViewVisibility(4);
			loadimage.setVisibility(View.VISIBLE);
			mHandler.sendEmptyMessageDelayed(22, 500);
		
		} else if (mic != null) {
			isMicOk();
		} else {
			setViewVisibility(2);
			beans = dbHelper.find();
			if (beans.size() == 0) {
				setViewVisibility(1);
				return;
			}
			adapter.addData(beans);
			mSearch_ListView.setAdapter(adapter);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search_button1:
		case R.id.search_button2:
			InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(mSearch_EditText.getWindowToken(), 0);
			setViewVisibility(4);
			clearDate();
			if (TextUtils.isEmpty(mSearch_EditText.getText())) {
				Toast.makeText(this, "搜索框没有内容。。", 500).show();
				return;
			}
			String search_field = mSearch_EditText.getText().toString();
			mSearch_EditText.setText("");
			new DBHelper(this).insert(search_field);
			search(search_field);

			break;

		case R.id.search_clear:
			dbHelper.delete();
			adapter.clear();
			adapter.notifyDataSetChanged();
			setViewVisibility(1);
			break;
		case R.id.s_tv1:
			if (!ivs[0].isShown()) {
				setImageVisibility(0);
				sortGoodsBeans("new_price", "asc");
				tvs[0].setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.sort_button_price_down, 0, 0, 0);
			} else {
				if (!bo) {
					tvs[0].setCompoundDrawablesWithIntrinsicBounds(
							R.drawable.sort_button_price_up, 0, 0, 0);
					sortGoodsBeans("new_price");
					bo = true;
				} else {
					sortGoodsBeans("new_price", "asc");
					tvs[0].setCompoundDrawablesWithIntrinsicBounds(
							R.drawable.sort_button_price_down, 0, 0, 0);
					bo = false;
				}
			}
			break;
		case R.id.s_tv2:
			sortGoodsBeans("scales_volume");
			tvs[0].setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.sort_button_price_none, 0, 0, 0);
			setImageVisibility(1);
			break;
		case R.id.s_tv3:
			sortGoodsBeans("praise_scale");
			tvs[0].setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.sort_button_price_none, 0, 0, 0);
			setImageVisibility(2);
			break;
		case R.id.s_tv4:
			tvs[0].setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.sort_button_price_none, 0, 0, 0);
			sortGoodsBeans("goods_name");
			setImageVisibility(3);
			break;
		case R.id.search_mic:
			isMicOk();
			break;
		case R.id.s_switch:

			if (mSearch_GridView.isShown()) {
				mSearch_GridView.setVisibility(View.GONE);
				mSearch_ListView.setVisibility(View.VISIBLE);
				switch_image.setImageResource(R.drawable.android_productlist_top_grid);
			} else {
				mSearch_GridView.setVisibility(View.VISIBLE);
				mSearch_ListView.setVisibility(View.GONE);
				switch_image.setImageResource(R.drawable.android_productlist_top_list);
			}
			break;
		}

	}

	public void sortGoodsBeans(String... str) {
		goodsBeans = dbHelper.queryGoods(str);
		adapterDetails.addData(goodsBeans);
		adapterDetails.notifyDataSetChanged();
		adapterGridView.addData(goodsBeans);
		adapterGridView.notifyDataSetChanged();
		
	}

	public void isMicOk() {
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() > 0) {
			startVoiceRecognitionActivity();
			
		} else {
			search_mic.setEnabled(false);
			Toast.makeText(this, "语音识别不可用", 500).show();
		}
	}

	private void startVoiceRecognitionActivity() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				"Speech recognition demo");
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			// Fill the list view with the strings the recognizer thought it
			// could have heard
			beans = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			setViewVisibility(4);
			tagMic=true;
			adapter=new SearchAdapter(this, beans);
			mSearch_ListView.setAdapter(adapter);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void setImageVisibility(int i) {
		for (int j = 0; j < ivs.length; j++) {
			if (j == i) {
				ivs[i].setVisibility(View.VISIBLE);
				tvs[i].setTextColor(Color.BLACK);
				continue;
			}
			ivs[j].setVisibility(View.GONE);
			tvs[j].setTextColor(Color.GRAY);
		}
	}

	public void setViewVisibility(int i) {
		for (int j = 0; j < views.size(); j++) {
			if (j == i) {
				views.get(i).setVisibility(View.VISIBLE);
				continue;
			}
			views.get(j).setVisibility(View.GONE);
		}
		if (s_title.isShown()) {
			switch_image.setVisibility(View.VISIBLE);
		} else {
			switch_image.setVisibility(View.GONE);
		}
	}

	public void search(String search_field) {
		startAnim();
		mSearch_ListView.setAdapter(adapterDetails);
		params = new JDParameters();
		params.add("search_field", search_field);
		getData(TAG_SEARCH, Constant.URL_SEARCH, params, "GET");
	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		String json = msg.getData().getString("json");
		
		switch (msg.what) {
		case TAG_SEARCH:
		case TAG_GOODS:
			s_linear.setVisibility(View.GONE);
			anim.stop();
			goodsBeans = JSONUtil.getGoodsJson(json);
			if (goodsBeans.size() < 1) {
				setViewVisibility(1);
				Toast.makeText(this, "没有匹配商品。。", 500).show();
				return;
			}
			setViewVisibility(0);
			setImageVisibility(1);
			dbHelper.insertGoods(goodsBeans);
			adapterDetails.addData(goodsBeans);
			adapterDetails.notifyDataSetChanged();
			adapterGridView.addData(goodsBeans);
			adapterGridView.notifyDataSetChanged();
			break;
			
		default:
			if (!s_title.isShown()) {			
				startAnim();
			}
			break;
		}

	}

	public void clearDate() {
		goodsBeans = new ArrayList<GoodsBean>();
		adapterDetails.addData(goodsBeans);
		adapterGridView.addData(goodsBeans);
		adapterDetails.notifyDataSetChanged();
		adapterGridView.notifyDataSetChanged();
	}

	public void startAnim() {
		loadimage.clearAnimation();
		s_linear.setVisibility(View.VISIBLE);
		anim.start();

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent(this, TabHostContent.class);
			intent.putExtra("TAG", "HomeActivity");
			ExitApplication.getInstance().addActivity2(this);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
