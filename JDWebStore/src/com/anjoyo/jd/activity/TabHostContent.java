package com.anjoyo.jd.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;
public class TabHostContent extends TabActivity implements OnClickListener{
	
	
	private TabHost mTabHost;
	private ImageView view1,view2,view3,view4,view5;
	private ImageView[]views={view1,view2,view3,view4,view5};
	private String[]name={"HomeActivity","SearchActivity","CategoryActivity","ShopCarActivity","MyselfActivity","Spick","CategoryDetial","SubCategoryActivity"};
	private Intent intentTag,intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhostcontant_activity);
		ExitApplication.getInstance().addActivity(this);
		views[0]=(ImageView) findViewById(R.id.contentdilplay_LinearLayout_ImageView0);
		views[0].setOnClickListener(this);
		views[1]=(ImageView) findViewById(R.id.contentdilplay_LinearLayout_ImageView1);
		views[1].setOnClickListener(this);
		views[2]=(ImageView) findViewById(R.id.contentdilplay_LinearLayout_ImageView2);
				views[2].setOnClickListener(this);
		views[3]=(ImageView) findViewById(R.id.contentdilplay_LinearLayout_ImageView3);
		views[3].setOnClickListener(this);
		views[4]=(ImageView) findViewById(R.id.contentdilplay_LinearLayout_ImageView4);
		views[4].setOnClickListener(this);
		
		// 初始化TabHost
		initeTabHost();
		
	}

	/**
	 * 初始化TAbHost
	 */
	private void initeTabHost() {
		// TODO Auto-generated method stub
		mTabHost = getTabHost();
		intentTag=getIntent();
		ExitApplication.getInstance().exit2();
		mTabHost.addTab(mTabHost.newTabSpec("HomeActivity").setIndicator("首页")
				.setContent(new Intent(this, HomeActivity.class)));
		intent=new Intent(this,SearchActivity.class);
		intent.putExtra("mic", intentTag.getStringExtra("mic"));
		intent.putExtra("category", intentTag.getStringExtra("category"));
		intent.putExtra("sub_category", intentTag.getStringExtra("sub_category"));
		intent.putExtra("s_sub_category", intentTag.getStringExtra("s_sub_category"));
		mTabHost.addTab(mTabHost.newTabSpec("SearchActivity").setIndicator("搜索")
				.setContent(intent));
		mTabHost.addTab(mTabHost.newTabSpec("CategoryActivity").setIndicator("分类")
				.setContent(new Intent(this, CategoryActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("ShopCarActivity").setIndicator("购物车")
				.setContent(new Intent(this, ShopCarActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("MyselfActivity").setIndicator("个人信息")
				.setContent(new Intent(this, MyselfActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("Spick").setIndicator("Spick")
				.setContent(new Intent(this, SpickActivity.class)));
		intent=new Intent(this,CategoryDetialActivity.class);
		intent.putExtra("sub_category", intentTag.getStringExtra("sub_category"));
		mTabHost.addTab(mTabHost.newTabSpec("CategoryDetial").setIndicator("CategoryDetial")
				.setContent(intent));	
		intent=new Intent(this,SubCategoryActivity.class);
		intent.putExtra("position", intentTag.getIntExtra("position", -1));
		mTabHost.addTab(mTabHost.newTabSpec("SubCategoryActivity").setIndicator("SubCategoryActivity")
				.setContent(intent));	
		String tag=intentTag.getStringExtra("TAG");	
		
		if("HomeActivity".equals(tag)){
			intentAcitivity(0);
		}
		
		else if("SearchActivity".equals(tag)){
			intentAcitivity(1);
		}
		else if("CategoryActivity".equals(tag)){
			intentAcitivity(2);
		}
		else if("ShopCarActivity".equals(tag)){
			intentAcitivity(3);
		}
		else if("MyselfActivity".equals(tag)){
			intentAcitivity(4);
		}
		else if("Spick".equals(tag)){
			intentAcitivity(5);
		}
		else if("CategoryDetial".equals(tag)){
			
			intentAcitivity(6);
		}
		else if("SubCategoryActivity".equals(tag)){
			intentAcitivity(7);
		}
		else{
			intentAcitivity(0);
		}
		
		
		
		
	}
	private void setBackground(int i){
		for (int j = 0; j < views.length; j++) {
			if (j==i) {
				views[i].setBackgroundResource(R.drawable.main_navigation_highlight_bg);
				continue;
			}
			views[j].setBackgroundResource(0);
		}
	}
	
	public void intentAcitivity(int i){
		mTabHost.setCurrentTabByTag(name[i]);
		switch (i) {
		
		case 5:
			setBackground(0);
			break;
		case 6:
		case 7:
			setBackground(2);
			break;

		default:
			setBackground(i);
			break;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.contentdilplay_LinearLayout_ImageView0:
			intentAcitivity(0);
			break;
		case R.id.contentdilplay_LinearLayout_ImageView1:
			intentAcitivity(1);			
			break;
		case R.id.contentdilplay_LinearLayout_ImageView2:
			intentAcitivity(2);			
			break;
		case R.id.contentdilplay_LinearLayout_ImageView3:
			intentAcitivity(3);		
			break;
		case R.id.contentdilplay_LinearLayout_ImageView4:
			intentAcitivity(4);			
			break;

		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuItem m=menu.add(1, 1, 1, "退出");
		m.setIcon(R.drawable.main_menu_exit);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		showDialog();
		
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.KEYCODE_BACK==keyCode) {
			showDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void showDialog() {
		AlertDialog.Builder ad=new Builder(this);
		ad.setTitle("退出");
		ad.setMessage("确定要退出？");
		ad.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ExitApplication.getInstance().exit();
			}
		});
		ad.setNegativeButton("取消", null);
		ad.show();
	}
	
}
