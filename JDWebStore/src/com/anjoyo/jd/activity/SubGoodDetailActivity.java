package com.anjoyo.jd.activity;



import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.GoodsBean;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class SubGoodDetailActivity extends TabActivity{
	private TabHost mTabHost;
	private RadioGroup mRadioGroup;
	private TextView mGoodDetail,mGoodNo,mJingDongPric;
	GoodsBean goodsBean;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_detail_text);
		intview();
		goodsBean=getIntent().getParcelableExtra("goodsBean");
		fillTextView();
		mTabHost=getTabHost();
		Intent intent=new Intent(SubGoodDetailActivity.this, BigPicActivity.class);
		intent.putExtra("url", goodsBean.getGoods_image());
		mTabHost.addTab(mTabHost.newTabSpec("goodmsg").setIndicator("goodmsg").setContent(intent));
		mTabHost.addTab(mTabHost.newTabSpec("spec").setIndicator("spec").setContent(new Intent(SubGoodDetailActivity.this, SpecificationAcitvity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("packlist").setIndicator("frends").setContent(new Intent(SubGoodDetailActivity.this,SpecificationAcitvity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("service").setIndicator("service").setContent(new Intent(SubGoodDetailActivity.this,SpecificationAcitvity.class)));
			
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb1:
					mTabHost.setCurrentTabByTag("goodmsg");
					break;
				case R.id.rb2:
					mTabHost.setCurrentTabByTag("spec");
					break;
				case R.id.rb3:
					mTabHost.setCurrentTabByTag("packlist");
					break;
				case R.id.rb4:
					mTabHost.setCurrentTabByTag("service");
					break;

				}
			}
		});
	}
	private void fillTextView() {
		mGoodDetail.setText(goodsBean.getGoods_name());
		mGoodNo.setText(goodsBean.getGoods_id()+"");
		mJingDongPric.setText("£¤£º"+goodsBean.getNew_price());	
		mRadioGroup=(RadioGroup) findViewById(R.id.rg);
	}
	private void intview() {
		// TODO Auto-generated method stub
		mGoodDetail=(TextView) findViewById(R.id.good_detail_item_text_textview1);
		mGoodNo=(TextView) findViewById(R.id.goodno);
		mJingDongPric=(TextView) findViewById(R.id.jingdongjia);
	}

}
