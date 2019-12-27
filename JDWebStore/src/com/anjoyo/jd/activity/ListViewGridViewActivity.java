package com.anjoyo.jd.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.ListGridViewAdapter;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.util.NotificationUtil;
/**
 * @author Yuan_Junhua
 */
public class ListViewGridViewActivity extends Activity{
	
		private Button mButton;
		private GridView mGridView;
		private ArrayList<GoodsBean> listBeans=new ArrayList<GoodsBean>();;
		private ListGridViewAdapter  adapter;
		private final int TAG_GRIDVIEW_LISTVIEW=306;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_gridview);
        Intent intent=getIntent();
        listBeans=intent.getParcelableArrayListExtra("bean");
        Log.d("mm", "handleMsg(Message msg)----->"+listBeans);
		
		
		adapter=new ListGridViewAdapter(this, listBeans);
		mButton=(Button) findViewById(R.id.homeactivity_viewpageritemone_threeSkipe_gridviewup_img);
		mGridView=(GridView) findViewById(R.id.homeactivity_viewpageritemone_threeSkipe_gridview);
		mGridView.setAdapter(adapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ListViewGridViewActivity.this, GoodDetailActivity.class);
				intent.putExtra("goods", listBeans.get(position));
				startActivity(intent);
				
			}
		});
		
	}
}
