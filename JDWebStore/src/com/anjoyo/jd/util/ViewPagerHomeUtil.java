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
		// ScrollView实现下拉刷新
		ElasticScrollView elasticScrollView = (ElasticScrollView) viewOne
				.findViewById(R.id.scrollview1);
		View scrollView = li.inflate(R.layout.pullrefresh_layoutitem, null);
		// 重写的ScrollView只能通过手动添加显示view布局
		elasticScrollView.addChild(scrollView, 1);

		
		/** title的控件初始化和监听 */
		EditText edit = (EditText) viewOne
				.findViewById(R.id.jd_home_title_edit);
		edit.setFocusable(false);
		ImageView recording = (ImageView) viewOne
				.findViewById(R.id.jd_home_title_recording);
		edit.setOnClickListener(this);
		recording.setOnClickListener(this);

		
		/** 头布局的3D效果 */
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
		
        //GestureDetector 操作手势类MyGestureListener
		final GestureDetector mGestureDetector = new GestureDetector(context,
				new MyGestureListener(imswitcher, ConstantHome.imageIds, vs,
						count));
		imswitcher.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				imswitcher.getParent().requestDisallowInterceptTouchEvent(true);//解决冲突，过滤掉父
				mGestureDetector.onTouchEvent(event);
				return true;
			}
		});

		//自动更换3D效果中的图片
		AutoChangePicture autoPic = new AutoChangePicture(imswitcher,
				ConstantHome.imageIds, vs, count);
		autoPic.execute(100);

		
		/** 添加掌上秒杀布局并初始化其中的控件 */
		scrollView.findViewById(
				R.id.viewPager_itemone_listView_viewPager_itemone)
				.setOnClickListener(this);
		// 使用服务器数据对掌上秒杀模块填充
		@SuppressWarnings("unused")
		ImageView spickGoodsImg = (ImageView) viewOne
				.findViewById(R.id.spick_leftimg);
		TextView spickTimeTv = (TextView) viewOne
				.findViewById(R.id.spick_timetv);
		TextView spickPriceTv = (TextView) viewOne
				.findViewById(R.id.spick_pricetv1);
		
		//制作秒杀计时器
		new CountdownUtil().countdown(spickTimeTv);
		String tvPriceNum = spickPriceTv.getText().toString();
		Spannable s = TextFormat.textFormat(tvPriceNum);
		spickPriceTv.setText(s);

		
		/**  初始化GridView模块控件并填充 */
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

		
		/** 对第GridView下的那个模块内容进行填充 */
		for (int i = 0; i < ConstantHome.imageId.length; i++) {
			ImageView imgView = (ImageView) viewOne
					.findViewById(ConstantHome.imageId[i]);
			imgView.setImageResource(ConstantHome.c_imgId[i]);
			imgView.setOnClickListener(this);
		}

		
		/** 对第 一个HorzontialListView内容进行填充 */
		HorzontialListView svListViewTop = (HorzontialListView) viewOne
				.findViewById(R.id.android_widget_Scroller_HorizontalListView_one);
		svListViewTop.setAdapter(svAdapterUp);
		svListViewTop.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				NotificationUtil.notificationUtil(context,
						"HorzontialListView" + position + "被点击");
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

		
		/**对第 二 个HorzontialListView内容进行填充  */
		HorzontialListView svListViewBottom = (HorzontialListView) viewOne
				.findViewById(R.id.android_widget_Scroller_HorizontalListView_two);
		svListViewBottom.setAdapter(svAdapterDown);
		svListViewBottom.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				NotificationUtil.notificationUtil(context,
						"HorzontialListView" + position + "被点击");
				Intent intent = new Intent(context,
						GoodDetailActivity.class);
				intent.putExtra("goods", list.get(1).get(position));
				context.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		//实现下拉刷新
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
		//针对title做出监听 
		case R.id.jd_home_title_edit://编辑监听
			intent.putExtra("TAG", "SearchActivity");
			context.startActivity(intent);
			break;
		case R.id.jd_home_title_recording://录音监听
			intent.putExtra("TAG", "SearchActivity");
			intent.putExtra("mic", "mic");
			context.startActivity(intent);
			break;
		// 对秒杀的布局进行监听 
		case R.id.viewPager_itemone_listView_viewPager_itemone:
			intent.putExtra("TAG", "Spick");
			context.startActivity(intent);
			break;
		}
	}
}
