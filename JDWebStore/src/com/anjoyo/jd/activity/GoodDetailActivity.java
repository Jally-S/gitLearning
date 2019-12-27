package com.anjoyo.jd.activity;

import com.anjoyo.jd.R;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.net.LoadImage;
import com.anjoyo.jd.util.JSONUtil;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GoodDetailActivity extends BaseActivity {
	private ImageView mGood_Detail_Imageview, mgood_detail_shopcar;
	private TextView mJiaGe, mYuanJia, mGoodDatial, mFill_Good_Shopcar,mCuXiaoMeg;
	private Spinner mGood_Detail_Spinner;
	GoodsBean goodsBean;
	private RelativeLayout mFirstPage;
	private RelativeLayout mSecondPage,mGoodDetailLayout;
	private ImageView mJianTouXia,mJianTouShang;
	private TextView mLoction,mgood_detail_pingjia;
	private ImageView mGoodDetailImageView;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_detail);

		Intent intent = getIntent();
		goodsBean = intent.getParcelableExtra("goods");
		initview();
		fuzhi();
		onClick();
	}

	private void onClick() {
		mGood_Detail_Imageview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(GoodDetailActivity.this, BigPicActivity.class);
				String url=goodsBean.getGoods_image();
				intent.putExtra("url", url);
				startActivity(intent);
			}
		});
		mGoodDetailLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(GoodDetailActivity.this,SubGoodDetailActivity.class );
				intent.putExtra("goodsBean", goodsBean);
				startActivity(intent);
			}
		});
		mFirstPage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mSecondPage.isShown()){
					mSecondPage.setVisibility(View.GONE);
					mJianTouShang.setVisibility(View.GONE);
					mJianTouXia.setVisibility(View.VISIBLE);
				}else{
					mSecondPage.setVisibility(View.VISIBLE);
					mJianTouShang.setVisibility(View.VISIBLE);
					mJianTouXia.setVisibility(View.GONE);
				}
				
				
				
			}
		});
	}

	private void fuzhi() {
		mJiaGe.setText("����"+goodsBean.getNew_price());
		mYuanJia.setText("ԭ�ۣ�" + goodsBean.getOld_price() + "");
		Log.d("result","goodsBean.getGoods_name()=="+goodsBean.getGoods_name() );
		Log.d("result", "goodsBean.getGoods_image()=="+goodsBean.getGoods_image());
		mGoodDatial.setText(goodsBean.getGoods_name().toString());
		mCuXiaoMeg.setText(goodsBean.getGoods_promotion());
		mLoction.setText(goodsBean.getGoods_location());
		mgood_detail_pingjia.setText(goodsBean.getScales_volume()+"������");
		new LoadImage(this).loadDrawable(goodsBean.getGoods_image(),
				mGood_Detail_Imageview);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, new String[] { "����",
						"�Ϻ�", "����", "����", "֣��", "����", "�人", "�ȷ�", "����", "������",
						"����", "��³ľ��", "����", "�ɶ�", "����", "�Ͼ�", "̨��", "����", "�ձ�",
						"����", });
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mGood_Detail_Spinner.setAdapter(adapter);
		mGood_Detail_Spinner.setSelection(0);
		mGood_Detail_Spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
		mgood_detail_shopcar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(GoodDetailActivity.this,
						TabHostContent.class);
				in.putExtra("TAG", "ShopCarActivity");
				startActivity(in);
			}
		});
		mFill_Good_Shopcar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				JDParameters params = new JDParameters();
				params.add("goods_id", goodsBean.getGoods_id());
				getData(TAG_SHOPCAROK, Constant.URL_INSERT_GOODS_CART, params,
						"POST");

			}
		});

	}

	private void initview() {
		mGoodDetailLayout=(RelativeLayout) findViewById(R.id.downlayout_mediallayout2);
		mGoodDetailImageView=(ImageView) findViewById(R.id.good_detail_imageview);
		mLoction=(TextView) findViewById(R.id.location);
		mJianTouShang=(ImageView) findViewById(R.id.jiantoushang);
		mJianTouXia=(ImageView) findViewById(R.id.jiantouxia);
		mFirstPage=(RelativeLayout) findViewById(R.id.first_pake);
		mSecondPage=(RelativeLayout) findViewById(R.id.second_pake);
		mGood_Detail_Imageview = (ImageView) findViewById(R.id.good_detail_imageview);
		mgood_detail_shopcar = (ImageView) findViewById(R.id.good_detail_shopcar);
		mJiaGe = (TextView) findViewById(R.id.jiage);
		mYuanJia = (TextView) findViewById(R.id.yuanjia);
		mGoodDatial = (TextView) findViewById(R.id.downlayout_mediallayout2_textview2);
		mFill_Good_Shopcar = (TextView) findViewById(R.id.downimageview3);
		mGood_Detail_Spinner = (Spinner) findViewById(R.id.good_detail_spinner);
		mCuXiaoMeg=(TextView) findViewById(R.id.cuoxiaomessage);
		mgood_detail_pingjia=(TextView) findViewById(R.id.good_detail_pingjia);
	}

	@Override
	public void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		if (msg.what == TAG_SHOPCAROK) {
			String json=msg.getData().getString("json");
			if (JSONUtil.parseRegister(json)==1) {
				
				AlertDialog.Builder ab = new Builder(GoodDetailActivity.this);
				ab.setTitle("��Ʒ��ӳɹ�");
				ab.setMessage("��Ʒ�Ѿ���ӵ���������");
				ab.setPositiveButton("ȥ���ﳵ", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent in1 = new Intent(GoodDetailActivity.this,
								TabHostContent.class);
						in1.putExtra("TAG", "ShopCarActivity");
						startActivity(in1);
					}
				});
				ab.setNegativeButton("�ٹ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				ab.show();
			}
			else
			{
				Toast.makeText(this, "���ﳵ��Ʒ����ظ�����", 500).show();
			}
		}
	}
}
