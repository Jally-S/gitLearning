package com.anjoyo.jd.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.SpickAdapter;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;
import com.anjoyo.jd.util.NotificationUtil;
/**
 * @author Yuan_Junhua
 */
public class SpickActivity extends BaseActivity {
	
	private ListView mListView;
	private Button spickBox;
	private ArrayList<GoodsBean> listBeans;
	private SpickAdapter mSpickAdater;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.spickactivity);
	listBeans=new ArrayList<GoodsBean>();
	mSpickAdater=new SpickAdapter(this, listBeans);
	JDParameters params=new JDParameters();
	getData(TAG_SQL_SPICK_GOODS, Constant.URL_SPIKE_GOODS, params, "GET");
	
	mListView=(ListView) findViewById(R.id.spickListView);
	spickBox=(Button) findViewById(R.id.spickButton);
	
	mListView.setAdapter(mSpickAdater);
	mListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			NotificationUtil.notificationUtil(SpickActivity.this, "已点击了这里");
			Intent in=new Intent(SpickActivity.this,GoodDetailActivity.class);
			in.putExtra("goods", listBeans.get(position));
			startActivity(in);
		}
	});
}
@Override
public void handleMsg(Message msg) {
	// TODO Auto-generated method stub
	if(msg.what==TAG_SQL_SPICK_GOODS){
		Bundle bundle=msg.getData();
		String json=bundle.getString("json");
		listBeans=JSONUtil.getGoodsJson(json);
		mSpickAdater.fillSpickData(listBeans);
		mSpickAdater.notifyDataSetChanged();
		
	}
}

}
