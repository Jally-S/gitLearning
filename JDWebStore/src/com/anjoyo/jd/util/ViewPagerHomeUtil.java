package com.anjoyo.jd.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

import com.anjoyo.jd.R;
import com.anjoyo.jd.activity.GoodDetailActivity;
import com.anjoyo.jd.activity.TabHostContent;
import com.anjoyo.jd.adapter.MyGridViewAdapter;
import com.anjoyo.jd.adapter.ScrollViewAdater;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.ConstantHome;
import com.anjoyo.jd.listener.MyGestureListener;
import com.anjoyo.jd.view.ElasticScrollView;
import com.anjoyo.jd.view.HorzontialListView;
import com.anjoyo.jd.view.ElasticScrollView.OnRefreshListener;

public class ViewPagerHomeUtil implements OnClickListener{

	private ArrayList<View> views;
	private LayoutInflater li;

	private Context context;
	private ScrollViewAdater svAdapterUp;
	private ScrollViewAdater svAdapterDown;
	private Handler mHandler;
	private int tag;
	private ImageSwitcher imswitcher;
	public ViewPagerHomeUtil(Context context,ArrayList<View> views,
			ScrollViewAdater svAdapterUp,ScrollViewAdater svAdapterDown,
			Handler mHandler,int tag) {
		this.context=context;
		this.views=views;
		this.svAdapterUp=svAdapterUp;
		this.svAdapterDown=svAdapterDown;
		this.mHandler=mHandler;
		this.tag=tag;
		li=LayoutInflater.from(context);
	}


	@SuppressWarnings("unchecked")
	public ElasticScrollView initeViewOneContrls(final ArrayList<ArrayList<GoodsBean>> list) {
		View viewOne = views.get(0);
		// ScrollViewʵ������ˢ��
		ElasticScrollView elasticScrollView = (ElasticScrollView) viewOne
				.findViewById(R.id.scrollview1);
		View scrollView = li.inflate(R.layout.pullrefresh_layoutitem, null);
		// ��д��ScrollViewֻ��ͨ���ֶ������ʾview����
		elasticScrollView.addChild(scrollView, 1);

		
		/** title�Ŀؼ���ʼ���ͼ��� */
		EditText edit = (EditText) viewOne
				.findViewById(R.id.jd_home_title_edit);
		edit.setFocusable(false);
		ImageView recording = (ImageView) viewOne
				.findViewById(R.id.jd_home_title_recording);
		edit.setOnClickListener(this);
		recording.setOnClickListener(this);

		
		/** ͷ���ֵ�3DЧ�� */
		int count = 0;
		imswitcher = (ImageSwitcher) viewOne
				.findViewById(R.id.imageSwitcher1);
		View[] vs = new View[ConstantHome.Rid.length];
		for (int i = 0; i < ConstantHome.Rid.length; i++) {
			vs[i] = viewOne.findViewById(ConstantHome.Rid[i]);
		}
		imswitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView imageView = new ImageView(context);
				imageView.setBackgroundColor(0xff0000);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return imageView;
			}
		});
		imswitcher.setImageResource(ConstantHome.imageIds[0]);
		
        //GestureDetector ����������MyGestureListener
		final GestureDetector mGestureDetector = new GestureDetector(context,
				new MyGestureListener(imswitcher, ConstantHome.imageIds, vs,
						count));
		imswitcher.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				imswitcher.getParent().requestDisallowInterceptTouchEvent(true);//�����ͻ�����˵���
				mGestureDetector.onTouchEvent(event);
				return true;
			}
		});

		//�Զ�����3DЧ���е�ͼƬ
		AutoChangePicture autoPic = new AutoChangePicture(imswitcher,
				ConstantHome.imageIds, vs, count);
		autoPic.execute(100);

		
		/** ���������ɱ���ֲ���ʼ�����еĿؼ� */
		scrollView.findViewById(
				R.id.viewPager_itemone_listView_viewPager_itemone)
				.setOnClickListener(this);
		// ʹ�÷��������ݶ�������ɱģ�����
		@SuppressWarnings("unused")
		ImageView spickGoodsImg = (ImageView) viewOne
				.findViewById(R.id.spick_leftimg);
		TextView spickTimeTv = (TextView) viewOne
				.findViewById(R.id.spick_timetv);
		TextView spickPriceTv = (TextView) viewOne
				.findViewById(R.id.spick_pricetv1);
		
		//������ɱ��ʱ��
		new CountdownUtil().countdown(spickTimeTv);
		String tvPriceNum = spickPriceTv.getText().toString();
		Spannable s = TextFormat.textFormat(tvPriceNum);
		spickPriceTv.setText(s);

		
		/**  ��ʼ��GridViewģ��ؼ������ */
		GridView gridView = (GridView) viewOne
				.findViewById(R.id.homeactivity_listview_item_two_grideview);
		ArrayList<HashMap<Integer, String>> listMap = new ArrayList<HashMap<Integer, String>>();
		for (int i = 0; i < ConstantHome.picId.length; i++) {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			map.put(ConstantHome.picId[i], ConstantHome.picName[i]);
			listMap.add(map);
		}
		gridView.setAdapter(new MyGridViewAdapter(context, listMap));
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, TabHostContent.class);
				intent.putExtra("category", ConstantHome.picName[position]);
				intent.putExtra("TAG", "SearchActivity");
				context.startActivity(intent);
			}
		});

		
		/** �Ե�GridView�µ��Ǹ�ģ�����ݽ������ */
		for (int i = 0; i < ConstantHome.imageId.length; i++) {
			ImageView imgView = (ImageView) viewOne
					.findViewById(ConstantHome.imageId[i]);
			imgView.setImageResource(ConstantHome.c_imgId[i]);
			imgView.setOnClickListener(this);
		}

		
		/** �Ե� һ��HorzontialListView���ݽ������ */
		HorzontialListView svListViewTop = (HorzontialListView) viewOne
				.findViewById(R.id.android_widget_Scroller_HorizontalListView_one);
		svListViewTop.setAdapter(svAdapterUp);
		svListViewTop.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				NotificationUtil.notificationUtil(context,
						"HorzontialListView" + position + "�����");
				Intent intent = new Intent(context,
						GoodDetailActivity.class);
				intent.putExtra("goods", list.get(0).get(position));
				context.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		
		/**�Ե� �� ��HorzontialListView���ݽ������  */
		HorzontialListView svListViewBottom = (HorzontialListView) viewOne
				.findViewById(R.id.android_widget_Scroller_HorizontalListView_two);
		svListViewBottom.setAdapter(svAdapterDown);
		svListViewBottom.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				NotificationUtil.notificationUtil(context,
						"HorzontialListView" + position + "�����");
				Intent intent = new Intent(context,
						GoodDetailActivity.class);
				intent.putExtra("goods", list.get(1).get(position));
				context.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		//ʵ������ˢ��
		elasticScrollView.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Message message = mHandler.obtainMessage();
						message.what = tag;
						mHandler.sendMessage(message);
					}
				});
				thread.start();
			}
		});

		return elasticScrollView;
	}


	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, TabHostContent.class);
		switch (v.getId()) {
		//���title�������� 
		case R.id.jd_home_title_edit://�༭����
			intent.putExtra("TAG", "SearchActivity");
			context.startActivity(intent);
			break;
		case R.id.jd_home_title_recording://¼������
			intent.putExtra("TAG", "SearchActivity");
			intent.putExtra("mic", "mic");
			context.startActivity(intent);
			break;
		// ����ɱ�Ĳ��ֽ��м��� 
		case R.id.viewPager_itemone_listView_viewPager_itemone:
			intent.putExtra("TAG", "Spick");
			context.startActivity(intent);
			break;
		}
	}
}
