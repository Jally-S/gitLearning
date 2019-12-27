package com.anjoyo.jd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.anjoyo.jd.listener.RequestListener;
import com.anjoyo.jd.net.AsyncJDRunner;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.NetworkUtil;

public abstract class BaseActivity extends Activity implements RequestListener {
	public  int REGISTER_TAG = 0x1;
	public int LOGIN_TAG=0x2;
	public static int CATEGORY_TAG=201;
	public static final int TAG_BARGIN=301;
	public static final int TAG_PULL_REFRESH=302;
	public static final int TAG_SQL_SELECT_HOT_SALE=303;
	public static final int SQL_SELECT_PRAISE_GOODS=304;
	public static final int TAG_SQL_SPICK_GOODS=305;
	public static final int TAG_SEARCH=401;
	public static final int TAG_GOODS=403;
	public static final int TAG_SUB_CATEGORY=402;
	public static final int TAG_SHOPCAROK=404;
	public static final int TAG_UPIMAGE=405;
	public static final int TAG_VERSION=406;
	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			handleMsg(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	/**
	 * 需要重写来获取json数据的抽象方法
	 * 
	 * @param msg
	 */
	public abstract void handleMsg(android.os.Message msg);

	@Override
	public void onComplete(int tag, String json) {
		// TODO Auto-generated method stub
		Log.d("GGG", "BaseActivity_onComplete=++++=" + json + "\t" + tag);

		Message msg = mHandler.obtainMessage();
		msg.what = tag;
		Bundle data = new Bundle();
		data.putString("json", json);
		msg.setData(data);
		mHandler.sendMessage(msg);
	}

	@Override
	public void onException(String json) {
		// TODO Auto-generated method stub
		Log.d("vv", "BaseActivity+onException=="+json);
		Toast.makeText(getApplicationContext(), "服务器请求数据异常。。。", 500).show();
	}

	/**
	 * 通过该方法请求服务器数据
	 * 
	 * @param tag
	 * @param url
	 * @param params
	 * @param method
	 */
	public void getData(int tag, String url, JDParameters params, String method) {
		if (!NetworkUtil.isNetwork(this)) {
			Toast.makeText(getApplicationContext(), "网络异常", 500).show();
			return;
		}
		AsyncJDRunner.request(tag, url, params, method, this);
	}
}
