package com.anjoyo.jd.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.ShopCarAdapter;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;
import com.anjoyo.jd.util.SharedPreferenceStorage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopCarActivity extends BaseActivity implements OnClickListener {
	protected static final int SHOP_CAR_DELETE_TAG = 99;
	private View empty_shop_car, shop_car;
	private LinearLayout shopcar_title;
	private Button titleDenglu, pay, emshopcart_go, shopcart_title_cancle;
	private CheckBox checkBox;
	private ListView mlv;
	private TextView zMoney, backMoney;
	private List<GoodsBean> mlist = new ArrayList<GoodsBean>();
	private int SHOP_CAR_TAG = 555;
	private ShopCarAdapter adapter;
	int deleteShopnum = 0;
	Set<GoodsBean> mbianHao;
	boolean bo = false, bo2 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		JDParameters params = new JDParameters();
		getData(SHOP_CAR_TAG, Constant.URL_SEARCH_GOODS_CART, params, "GET");
		setContentView(R.layout.shopcaractivity);
		initViews();
	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		String json = msg.getData().getString("json");
		if (msg.what == SHOP_CAR_TAG) {
			Log.d("aa", "aa====json====" + json);
			mlist = JSONUtil.getGoodsJson(json);
			adapter.addData(mlist);
			adapter.notifyDataSetChanged();
			changeView();
		}
		if (msg.what == SHOP_CAR_DELETE_TAG) {
			Log.d("aa", "aa===json==" + json);
			Log.d("aa", "pan duan====shifou qing qiu cheng gong ");
			int state = JSONUtil.parseDeletShopCar(json);
			if (state == 001) {
				Toast.makeText(this, "请求删除成功", 500).show();
				JDParameters params = new JDParameters();
				getData(SHOP_CAR_TAG, Constant.URL_SEARCH_GOODS_CART, params,
						"GET");
			} else if (state == 010)
				Toast.makeText(this, "没有匹配商品", 500).show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.shopcart_title_denglu:
			Intent in = new Intent();
			in.putExtra("shopcar_tag", 1);
			in.setClass(this, LoginActivity.class);
			startActivity(in);
			break;
		case R.id.emshopcart_go:
			startActivity(new Intent(this, HomeActivity.class));
			break;

		case R.id.shopcart_pay:
			break;
		case R.id.shopcart_title_cancle:
			showDeleteDialog();

			break;
		default:
			break;
		}

	}

	/**
	 * 删除购物车商品
	 */
	private void showDeleteDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialog = new Builder(this);
		dialog.setTitle("请问你是否要删除这" + deleteShopnum + "种商品？");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("aa", "qu delete");
				JDParameters params = new JDParameters();
				for (GoodsBean o : mbianHao) {
					params.add("goods_id", o.getGoods_id());
				}
				// 删除后重新请求服务器，刷新列表了
				getData(SHOP_CAR_DELETE_TAG, Constant.URL_DELETE_GOODS_CART,
						params, "POST");
				Log.d("aa", "qing qiu wan bi ");
			}
		});

		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}

	private void initViews() {
		// TODO Auto-generated method stub

		shopcar_title = (LinearLayout) findViewById(R.id.shopcart_title_layout_denglu);
		empty_shop_car = (RelativeLayout) findViewById(R.id.empty_shopcart_title);
		shop_car = (RelativeLayout) findViewById(R.id.shop_cart);
		shopcart_title_cancle = (Button) findViewById(R.id.shopcart_title_cancle);
		titleDenglu = (Button) findViewById(R.id.shopcart_title_denglu);
		pay = (Button) findViewById(R.id.shopcart_pay);
		checkBox = (CheckBox) findViewById(R.id.shopcart_check);
		zMoney = (TextView) findViewById(R.id.shopcart_truemoney);
		backMoney = (TextView) findViewById(R.id.shopcart_backmoney);
		emshopcart_go = (Button) findViewById(R.id.emshopcart_go);
		mlv = (ListView) findViewById(R.id.shopcart_lv);

		if (SharedPreferenceStorage.getLoginUser(this) != null) {
			shopcar_title.setVisibility(View.GONE);
		}
		shopcart_title_cancle.setOnClickListener(this);
		titleDenglu.setOnClickListener(this);
		pay.setOnClickListener(this);
		emshopcart_go.setOnClickListener(this);

		mlist = new ArrayList<GoodsBean>();
		adapter = new ShopCarAdapter(this, mlist, new ZongJi() {
			@Override
			public void changeZongJi(double zmoney) {

				Log.d("aa", "aa===zmoney===" + zmoney);
				// TODO Auto-generated method stub
				zMoney.setText("￥" + zmoney);
				Log.d("aa", zMoney.getText().toString());
			}

			@Override
			public void deleteShopNumber(Set<GoodsBean> bianHao) {
				// TODO Auto-generated method stub
				mbianHao = bianHao;
				deleteShopnum = bianHao.size();
				if (deleteShopnum == mlist.size()) {
					Log.d("aa", "aa====activity===quan xuan");
					bo2 = true;
					checkBox.setChecked(true);

				} else {
					bo = true;
					checkBox.setChecked(false);

				}
			}
		});
		mlv.setAdapter(adapter);
		mlv.setVisibility(View.GONE);

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Log.d("aa", "hahahah====yes" + isChecked);
					if (!bo2)
						adapter.getAllChecked();

				} else {
					if (!bo) {
						adapter.getNoChecked();

					}

					Log.d("aa", "heihei===no" + isChecked);
				}
				bo = false;
				bo2 = false;
				adapter.notifyDataSetChanged();
			}
		});

	}

	private void changeView() {
		// TODO Auto-generated method stub
		if (mlist.size() > 0) {
			Log.d("aa", "===shop_car===有数据" + mlist.get(0).getGoods_name());
			shop_car.setVisibility(View.VISIBLE);
			empty_shop_car.setVisibility(View.GONE);

			mlv.setVisibility(View.VISIBLE);
			Log.d("aa", "aa====checkedid2===" + mlist.size());

		} else {
			Log.d("aa", "aa====解析购物车商品===无数据");
			shop_car.setVisibility(View.GONE);
			mlv.setVisibility(View.GONE);
			empty_shop_car.setVisibility(View.VISIBLE);
		}
	}

	public interface ZongJi {

		public void changeZongJi(double zmoney);

		public void deleteShopNumber(Set<GoodsBean> bianHao);
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
