package com.anjoyo.jd.activity;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;

public class JDMainActivity extends Activity {
	public static	final int TAG_MESSAGE_DELAYED=100;
	private Handler mHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case TAG_MESSAGE_DELAYED:
				startActivity(new Intent(JDMainActivity.this, TabHostContent.class));
				overridePendingTransition( R.anim.push_up_in,R.anim.push_up_out);
				break;

			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jdmain_activity);
		ExitApplication.getInstance().addActivity2(this);
		//动画效果
		animationMethod();
	}

	/**
	 * 动画效果显示
	 */
	private void animationMethod() {
		// TODO Auto-generated method stub
		Message msg=mHandler.obtainMessage();
		msg.what=TAG_MESSAGE_DELAYED;
		mHandler.sendMessageDelayed(msg, 1000);
	}
}
