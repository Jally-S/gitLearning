package com.anjoyo.jd.activity;

import com.anjoyo.jd.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FastRegisterActivity extends BaseActivity implements
		OnClickListener {
	private Button register_nomol_zhuce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fast_registery);
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		register_nomol_zhuce = (Button) findViewById(R.id.register_nomol_zhuce);

		register_nomol_zhuce.setOnClickListener(this);
	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_nomol_zhuce:
			Log.d("aa", "tiao zhuan");
			startActivity(new Intent(this, NormalRegisterActivity.class));
			break;

		default:
			break;
		}
	}

}
