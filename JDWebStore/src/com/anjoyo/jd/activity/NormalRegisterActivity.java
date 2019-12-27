package com.anjoyo.jd.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Text;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.User;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.JSONUtil;
import com.anjoyo.jd.util.MD5Util;
import com.anjoyo.jd.util.NotificationUtil;
import com.anjoyo.jd.util.SharedPreferenceStorage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

public class NormalRegisterActivity extends BaseActivity implements
		OnClickListener, OnFocusChangeListener {
	private EditText userName, passWord, email, ensure_password;
	private CheckBox showPassWord, agree;
	private ImageView warn1, warn2, warn3, warn4;
	private Button zhuce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.normal_registery);
		initViews();

	}

	@SuppressLint("CutPasteId")
	private void initViews() {
		// TODO Auto-generated method stub
		warn1 = (ImageView) findViewById(R.id.warn1);
		warn2 = (ImageView) findViewById(R.id.warn2);
		warn3 = (ImageView) findViewById(R.id.warn3);
		warn4 = (ImageView) findViewById(R.id.warn4);
		userName = (EditText) findViewById(R.id.normal_register_username);
		passWord = (EditText) findViewById(R.id.normal_register_password);
		ensure_password = (EditText) findViewById(R.id.normal_register_ensure);
		email = (EditText) findViewById(R.id.normal_register_email);
		showPassWord = (CheckBox) findViewById(R.id.nomal_register_showpassword);
		agree = (CheckBox) findViewById(R.id.nomal_registery_agree);
		zhuce = (Button) findViewById(R.id.normal_registery_zhuce);
		zhuce.setOnClickListener(this);

		userName.setOnFocusChangeListener(this);
		passWord.setOnFocusChangeListener(this);
		ensure_password.setOnFocusChangeListener(this);
		email.setOnFocusChangeListener(this);

		showPassWord.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton v, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Log.d("aa", "aa====ischecked1" + isChecked);
					passWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					ensure_password
							.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				} else {
					Log.d("aa", "aa====ischecked2" + isChecked);
					passWord.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					ensure_password.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}
				Editable etable = passWord.getText();
				Editable etable_ensure = ensure_password.getText();

				Selection.setSelection(etable, etable.length());
				Selection.setSelection(etable_ensure, etable_ensure.length());

			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String pw = passWord.getText().toString();
		String ensure_pw = ensure_password.getText().toString();
		switch (v.getId()) {
		case R.id.normal_registery_zhuce:
			if (pw.equals(ensure_pw) && agree.isChecked()&&!"".equals(pw)) {
				JDParameters params = new JDParameters();
				params.add("username", userName.getText().toString());
				params.add("password",
						MD5Util.stringMD5(passWord.getText().toString()));
				params.add("email", email.getText().toString());
				getData(REGISTER_TAG, Constant.URL_REGISTER, params, "POST");
			} else
				NotificationUtil.notificationUtil(this, "请填写正确再注册");
			break;
		}

	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		String json = msg.getData().getString("json");
		if (msg.what == REGISTER_TAG) {
			int state = JSONUtil.parseRegister(json);
			if (state == 001) {
				NotificationUtil.notificationUtil(this, "注册成功");

				JDParameters params = new JDParameters();
				params.add("username", userName.getText().toString());
				params.add("password",
						MD5Util.stringMD5(passWord.getText().toString()));
				getData(LOGIN_TAG, Constant.URL_LOGIN, params, "POST");

			} else if (state == 002) {
				NotificationUtil.notificationUtil(this, "账号已注册");
			}

		} else if (msg.what == LOGIN_TAG) {
			User user = JSONUtil.parseLogin(json);
			if (user != null) {
				SharedPreferenceStorage.saveLoginUserId(this, user);
				ExitApplication. getInstance().addActivity2(this);
				Intent intent = new Intent(this, TabHostContent.class);
				intent.putExtra("TAG", "MyselfActivity");
				startActivity(intent);
			}
		}
	}

	/*
	 * 验证输入正确性(non-Javadoc)
	 * 
	 * @see
	 * android.view.View.OnFocusChangeListener#onFocusChange(android.view.View,
	 * boolean)
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.normal_register_username:
			if (!hasFocus) {
				String text = userName.getText().toString();
				if (TextUtils.isEmpty(text))
					warn1.setVisibility(View.VISIBLE);
				else
					warn1.setVisibility(View.GONE);
			}

			break;
		case R.id.normal_register_email:
			if (!hasFocus) {
				String text = email.getText().toString();
				boolean bo = spanEmailFormat(text);
				if (TextUtils.isEmpty(text) || !bo)
					warn2.setVisibility(View.VISIBLE);
				else
					warn2.setVisibility(View.GONE);
			}
			break;

		case R.id.normal_register_password:
			if (!hasFocus) {
				String text = passWord.getText().toString();

				if (TextUtils.isEmpty(text))
					warn3.setVisibility(View.VISIBLE);
				else
					warn3.setVisibility(View.GONE);
			}
			break;

		case R.id.normal_register_ensure:
			if (!hasFocus) {
				String text = ensure_password.getText().toString();

				if (TextUtils.isEmpty(text)
						|| !passWord.getText().toString().equals(text))
					warn4.setVisibility(View.VISIBLE);
				else
					warn4.setVisibility(View.GONE);
			}
			break;

		}

	}

	public boolean spanEmailFormat(String email) {
		String ss = "\\w+@\\w+\\.\\p{Alpha}{2,4}(\\.\\p{Alpha}{2})?";
		Pattern p = Pattern.compile(ss);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
