package com.anjoyo.jd.activity;

import com.anjoyo.jd.R;
import com.anjoyo.jd.net.LoadImage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class BigPicActivity extends Activity{
private ImageView mImageView;
	protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.bigimageview);
	mImageView=(ImageView) findViewById(R.id.bigimageview_imageview);
	new LoadImage(this).loadDrawable(getIntent().getStringExtra("url"),
			mImageView);
}
}
