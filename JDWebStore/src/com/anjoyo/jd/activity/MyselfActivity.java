package com.anjoyo.jd.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anjoyo.jd.ExitApplication;
import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.User;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.net.LoadImage;
import com.anjoyo.jd.util.SharedPreferenceStorage;
import com.anjoyo.jd.util.VersionUpdateUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyselfActivity extends BaseActivity implements OnClickListener {
	private Button login, login_back;
	private TextView mUsername;
	private ImageView touXiang;
	private int REQUEST_CODE_PICTURE = 1;
	private int REQUEST_CAMERA = 2;
	private Uri mPhotoUri = null;
	private Bitmap bm = null;
	private String imgPath = "";
	private User user;
	private LinearLayout myNoLogin, myLogin;
	Dialog ab;
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myself_activity);
		initViews();
		user = SharedPreferenceStorage.getLoginUser(this);
		if (user != null) {
			String username = user.getUsername();
			mUsername.setText(username);
			if (user.getProfile_image() != null) {
				new LoadImage(this).loadDrawable(user.getProfile_image(),
						touXiang);
			}
			myNoLogin.setVisibility(View.GONE);
			myLogin.setVisibility(View.VISIBLE);
			login_back.setVisibility(View.VISIBLE);
		} else {
			myNoLogin.setVisibility(View.VISIBLE);
			myLogin.setVisibility(View.GONE);
			login_back.setVisibility(View.GONE);
		}

	}

	private void initViews() {
		// TODO Auto-generated method stub
		login = (Button) findViewById(R.id.myactivity_denglu);
		login_back = (Button) findViewById(R.id.login_back);
		myNoLogin = (LinearLayout) findViewById(R.id.my_no_login);
		myLogin = (LinearLayout) findViewById(R.id.mylogin);
		mUsername = (TextView) findViewById(R.id.my_username);
		touXiang = (ImageView) findViewById(R.id.my_touxiang);
		view = findViewById(R.id.update_version);
		view.setOnClickListener(this);
		login.setOnClickListener(this);
		touXiang.setOnClickListener(this);
		login_back.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.myactivity_denglu:
			Intent in = new Intent();
			in.putExtra("myself_tag", 2);
			in.putExtra("Bitmap", bm);// 通过parceLableValue 来传
			in.setClass(MyselfActivity.this, LoginActivity.class);
			startActivity(in);

			break;
		case R.id.my_touxiang:
			showDialog();
			break;

		case R.id.login_back:
			showBackDialog();
			break;
		case R.id.paizhao_pic:
			Intent intent_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 往系统数据库中的图片表中的某些列添加数据；
			ContentValues values = new ContentValues();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String fileName = format.format(new Date());
			values.put("title", fileName);// 标题 values.put("_display_name",
											// fileName);// 显示名字
			values.put("picasa_id", fileName);// picasa_id
			values.put("description", fileName);
			// 往数据库存放图片的地址上再插入一个图片，并返回这个新图片的地址mPhotoUri; mPhotoUri =
			getContentResolver()
					.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							values);
			intent_camera.putExtra("output", mPhotoUri);
			startActivityForResult(intent_camera, REQUEST_CAMERA); // developer.baidu.com/map;
			break;

		case R.id.bendi_pic:
			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, REQUEST_CODE_PICTURE);

			break;
		case R.id.update_version:
			JDParameters params = new JDParameters();
			getData(TAG_VERSION, Constant.URL_UPVERSON, params, "GET");
			break;
		default:
			break;
		}
	}

	private void showBackDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialog = new Builder(this);
		dialog.setTitle("退出登录");
		dialog.setMessage("确定要退出吗？");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				SharedPreferenceStorage.saveLoginUserId(MyselfActivity.this,
						new User());
				startActivity(new Intent(MyselfActivity.this,
						TabHostContent.class));
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

	private void showDialog() {
		// TODO Auto-generated method stub
		ab = new Dialog(this);
		ab.setTitle("上传头像");
		ab.setCancelable(true);
		LayoutInflater inflater = getLayoutInflater().from(this);
		View v = inflater.inflate(R.layout.shang_chuan_dialog, null);
		ab.setContentView(v);
		TextView takePhoto = (TextView) v.findViewById(R.id.paizhao_pic);
		TextView benDiPhoto = (TextView) v.findViewById(R.id.bendi_pic);

		takePhoto.setOnClickListener(this);
		benDiPhoto.setOnClickListener(this);

		ab.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_PICTURE) {
			if (data == null) {
				return;
			}
			mPhotoUri = data.getData();
		}
		displayImage();
	}

	private void displayImage() {
		// TODO Auto-generated method stub
		imgPath = getImagePathFromUri(mPhotoUri);// 把Uri转化成图片路径；
		JDParameters params = new JDParameters();
		params.add("profile_image", imgPath);
		params.add("user_id", user.getUser_id());
		SharedPreferenceStorage.saveLoginUserImage(this, imgPath);
		getData(TAG_UPIMAGE, Constant.URL_UP_PROFILE_IMAGE, params, "POST");
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;// 第三个参数opt,是控制内存溢出的；
		try {
			// 如何把一个Uri地址图片转化成一个图片；BitmapFactory.decodeStream()
			bm = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(mPhotoUri), null, opt);
			touXiang.setImageBitmap(bm);
			ab.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getImagePathFromUri(Uri uri) {
		Cursor c = getContentResolver().query(uri, new String[] { "_data" },
				null, null, null);
		if (c.moveToNext()) {
			imgPath = c.getString(c.getColumnIndex("_data"));
		}
		return imgPath;
	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case TAG_VERSION:
			new VersionUpdateUtil(this).check(msg.getData().getString("json"));
			break;

		default:
			break;
		}
	}

	@Override
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
