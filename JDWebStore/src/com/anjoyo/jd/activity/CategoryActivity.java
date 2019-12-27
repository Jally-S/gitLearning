package com.anjoyo.jd.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.GoodsBean;
import com.slidingmenu.lib.SlidingMenu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CategoryActivity extends Activity {
	private static int TAG = 0;
	SimpleAdapter adapter ;
	private TextView mRightTextView;
	private ListView mCategory_Activity_ListView;
	private int imageView[] = { R.drawable.category_yunyingfushi,
			R.drawable.category_pixiexuezi, R.drawable.shuma,
			R.drawable.bangong, R.drawable.category_beauty, R.drawable.tushu,
			R.drawable.naifen, R.drawable.homedianqi };
	private String textName[] = { "服饰", "鞋靴", "数码", "办公", "个人化妆品", "图书",
			"母婴", "家用电器" };
	private String textCategroy[] = { "男装/女装/内衣", "流行男鞋/时尚女鞋", "电脑/照相机/手机",
			"办公用品/家具", "面部护理/身体护理", "历史/技术/小说", "奶粉/保健", "大家电/生活电器/厨房电器" };
	private SlidingMenu menu;
	private List<GoodsBean> beans;
	private HashMap<String, HashMap<String, HashMap<String, ArrayList<GoodsBean>>>> goodsBean;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_activity);
		intentview();
		filldata();
		intentToOther();
		
	}


	private void intentToOther() {
		mCategory_Activity_ListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent=new Intent(CategoryActivity.this,TabHostContent.class);
				intent.putExtra("position", position);
				intent.putExtra("TAG", "SubCategoryActivity");
				startActivity(intent);
			}
		});
	}


	private void filldata() {
		ArrayList<Map<String, ?>> data=new ArrayList<Map<String,?>>();
		data= filldata(data);
		// TODO Auto-generated method stub
		String[] from = { "iv", "textName", "textCategory" };
		int[] to = { R.id.category_activity_item_imageview,
				R.id.category_activity_item_name,
				R.id.category_activity_item_category, };
		adapter=new SimpleAdapter(this, data, R.layout.category_activity_item, from, to);
		mCategory_Activity_ListView.setAdapter(adapter);
	}


private ArrayList<Map<String, ?>> filldata(ArrayList<Map<String, ?>> data) {
	for (int i = 0; i < imageView.length; i++) {
		Map m = new HashMap();
		m.put("iv", imageView[i]);
		m.put("textName", textName[i]);
		m.put("textCategory", textCategroy[i]);
		m.put("textright", textName[i]);
		data.add(m);
	}
	return data;
	}

	private void intentview() {
		mCategory_Activity_ListView=(ListView) findViewById(R.id.category_activity_listview);
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent(CategoryActivity.this, TabHostContent.class);
			intent.putExtra("TAG", "HomeActivity");
			ExitApplication.getInstance().addActivity2(this);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
