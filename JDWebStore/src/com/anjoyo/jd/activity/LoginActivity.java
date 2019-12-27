package com.anjoyo.jd.activity;

import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.User;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;
import com.anjoyo.jd.util.MD5Util;
import com.anjoyo.jd.util.NotificationUtil;
import com.anjoyo.jd.util.SharedPreferenceStorage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost.TabContentFactory;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private ImageView headPortrait;
	private EditText username, password;
	private Button loginDenglu, zhuce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initViews();
	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		if (msg.what == LOGIN_TAG) {
			String json = msg.getData().getString("json");
			User user = JSONUtil.parseLogin(json);
			if (user!=null) {
				NotificationUtil.notificationUtil(this, "登陆成功！");
				SharedPreferenceStorage.saveLoginUserId(this, user);
				if (getIntent().getIntExtra("myself_tag", 0) == 2) {
					Intent intent=new Intent(this, TabHostContent.class);
					intent.putExtra("TAG", "MyselfActivity");
					startActivity(intent);
				} else if (getIntent().getIntExtra("shopcar_tag", 0) == 1){
					Intent intent=new Intent(this, TabHostContent.class);
					intent.putExtra("TAG", "ShopCarActivity");
					startActivity(intent);
				}
			} else  {
				NotificationUtil.notificationUtil(this, "用户名和密码不匹配！");
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_denglu:
			JDParameters params = new JDParameters();
			params.add("username", username.getText().toString());
			params.add("password", MD5Util.stringMD5(password.getText().toString()));
			getData(LOGIN_TAG, Constant.URL_LOGIN, params, "POST");
			break;
		case R.id.zhuce:
			startActivity(new Intent(this, FastRegisterActivity.class));
			break;

		default:
			break;
		}
	}

	private void initViews() {
		// TODO Auto-generated method stub
		headPortrait = (ImageView) findViewById(R.id.headportrait);
		username = (EditText) findViewById(R.id.login_username);
		password = (EditText) findViewById(R.id.login_password);
		loginDenglu = (Button) findViewById(R.id.login_denglu);
		zhuce = (Button) findViewById(R.id.zhuce);
		headPortrait.setOnClickListener(this);
		loginDenglu.setOnClickListener(this);
		zhuce.setOnClickListener(this);
		Bitmap bm = getIntent().getParcelableExtra("Bitmap");
		if (bm != null) {
			headPortrait.setImageBitmap(bm);
		}

	}

}
