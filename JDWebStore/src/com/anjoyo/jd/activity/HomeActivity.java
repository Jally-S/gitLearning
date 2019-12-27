package com.anjoyo.jd.activity;
	import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
	import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;
import android.widget.ListView;

import com.anjoyo.jd.ExitApplication;
	import com.anjoyo.jd.R;
import com.anjoyo.jd.adapter.ListViewAdapter;
import com.anjoyo.jd.adapter.MyPagerAdapter;
import com.anjoyo.jd.adapter.ScrollViewAdater;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.constant.ConstantHome;
import com.anjoyo.jd.listener.GuidePageChangeListener;
import com.anjoyo.jd.net.JDParameters;
import com.anjoyo.jd.util.CountdownUtil;
import com.anjoyo.jd.util.JSONUtil;
import com.anjoyo.jd.util.ViewPagerExceptHomeactivityUtil;
import com.anjoyo.jd.util.ViewPagerHomeUtil;
import com.anjoyo.jd.view.ElasticScrollView;
import com.anjoyo.jd.view.HorzontialListView;
/**
 * @author Yuan_Junhua
 */
	public class HomeActivity extends BaseActivity  {
		private ViewPager mViewPager;
		private View[] viewLayout;
		private ArrayList<View> views;
		private ArrayList<Integer> lvBeanPagerTwo, lvBeanPagerThree;
		private ArrayList<GoodsBean> svBeanPagerOneTop, svBeanPagerTwoTop, svBeanOnePagerBottom,svBeanPagerThreeTop;
		private ScrollViewAdater svAdapterOnePagerTop, svAdapterPagerTwoTop, svAdapterOnePagerBottom, svAdapterThreePagerTop;
		private ListViewAdapter lvAdapterTwoPager, lvAdapterThreePager;
		private ElasticScrollView elasticScrollView;
		private LayoutInflater li;
		private ImageView imageViews[];
		private ImageView imageView;
		private boolean isContinue = true;
		private AtomicInteger what = new AtomicInteger(0);
		
	    private ArrayList<ArrayList<GoodsBean>> vpList;	
	    private ArrayList<ArrayList<GoodsBean>> vpHomeList;	

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.homeactivity);
			mViewPager = (ViewPager) findViewById(R.id.home_activity_viewPager);
			li=LayoutInflater.from(this);
			vpList=new ArrayList<ArrayList<GoodsBean>>();
			vpHomeList=new ArrayList<ArrayList<GoodsBean>>();
			
			svBeanPagerOneTop=new ArrayList<GoodsBean>();
			lvBeanPagerTwo=new ArrayList<Integer>();
			svBeanPagerTwoTop=new ArrayList<GoodsBean>();
			svBeanOnePagerBottom=new ArrayList<GoodsBean>();
			svBeanPagerThreeTop=new ArrayList<GoodsBean>();
			lvBeanPagerThree=new ArrayList<Integer>();
			//��ʼ������viewPagerҳ�����ʾҳ�����
			viewLayout = new View[3];
			
			//���ڶ�ҳ�͵���ҳ��ListViewͼƬ
			fillListBeansPic();
			
			
			//��ȡ������������Ϣ
			JDParameters params=new JDParameters();
			getData(TAG_BARGIN,Constant.URL_SPIKE_GOODS, params, "GET");
			JDParameters paramsTwo=new JDParameters();
			paramsTwo.add("sql",Constant.SQL_SELECT_HOT_SALE);
			getData(TAG_SQL_SELECT_HOT_SALE, Constant.URL_SELECT_GOODS,paramsTwo,"GET");
			JDParameters paramsThree=new JDParameters();
			paramsThree.add("sql",Constant.SQL_SELECT_PRAISE_GOODS);
			getData(SQL_SELECT_PRAISE_GOODS, Constant.URL_SELECT_GOODS,paramsThree,"GET");
			
			svAdapterOnePagerTop=new ScrollViewAdater(this,svBeanPagerOneTop);
			svAdapterPagerTwoTop=new ScrollViewAdater(this, svBeanPagerTwoTop);
			svAdapterOnePagerBottom=new ScrollViewAdater(this, svBeanOnePagerBottom);
			svAdapterThreePagerTop=new ScrollViewAdater(this, svBeanPagerThreeTop);
			lvAdapterTwoPager=new ListViewAdapter(this,lvBeanPagerTwo);
			lvAdapterThreePager=new ListViewAdapter(this, lvBeanPagerThree);
			
			
			// ������ViewPager�еĲ���ת��ΪView��������ҳ��ҳ������Ч��
			initeLayout();
			//��һ��viewPager���������
			initeViewPagerOne();
			//�ڶ���viewPager���������
			initeViewPagerTwo();
			//������viewPager���������
			initeViewPagerThree();
		}


		/**��ViewPager�еĲ���ת��ΪView */
		private void initeLayout() {
			// TODO Auto-generated method stub
			int[] rId = new int[] { R.layout.viewpager_item_one,
					R.layout.viewpager_item_two, R.layout.viewpager_item_three };
			for (int i = 0; i < rId.length; i++) {
				viewLayout[i] = li.inflate(rId[i], null);
			}
			views = new ArrayList<View>();
			for (int i = 0; i < viewLayout.length; i++) {
				views.add(viewLayout[i]);
			}

			// ���ҳ����ʾ������viewPager����view
			mViewPager.setAdapter(new MyPagerAdapter(views));

			// ViewPager��ҳ��ҳ������Ч��
			ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
			imageViews = new ImageView[views.size()];
			for (int i = 0; i < views.size(); i++) {
				imageView = new ImageView(this);
				imageView.setLayoutParams(new LayoutParams(18, 8));
				imageView.setPadding(10, 0, 0, 0);// �� �� �� ��
				imageViews[i] = imageView;
				if (i == 0) {
					// Ĭ��ѡ�е�һ��ͼƬ
					imageViews[i].setImageResource(R.drawable.control_circled);
				} else {
					imageViews[i].setImageResource(R.drawable.control_circleh);
				}
				group.addView(imageViews[i]);
			}

			mViewPager.setOnPageChangeListener(new GuidePageChangeListener(
					imageViews));//viewPager �л�ʱ�� Բ�������ֻ�Ч��
			mViewPager.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						isContinue = false;
						break;
					case MotionEvent.ACTION_UP:
						isContinue = true;
						break;
					default:
						isContinue = true;
						break;
					}
					return false;
				}
			});
			new Thread(new Runnable() {
				@Override
				public void run() {
					if (isContinue) {
//						mHandler.sendEmptyMessage(what.get());
						whatOption();//ҳ������Ч����ָ��ҳ��ļ�����
					}
				}

			}).start();
		}

		/** ҳ������Ч�� ��ָ��ҳ��ļ����� */
		private void whatOption() {
			what.incrementAndGet();
			if (what.get() > imageViews.length - 1) {
				what.getAndAdd(-4);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
		}

		private void fillListBeansPic() {
			for (int i = 0; i < ConstantHome.viewPagerTwoListView.length; i++) {
				lvBeanPagerTwo.add(ConstantHome.viewPagerTwoListView[i]);
				lvBeanPagerThree.add(ConstantHome.viewPagerThreeListView[i]);
			}
		}

		/**
		 * �Ե�����viewPager��������
		 */
		private ViewPagerExceptHomeactivityUtil vpClassThree;
		private void initeViewPagerThree() {
			
			View view = views.get(2);
			View headHorzontialLayout = li.inflate(
					R.layout.homeactivity_viewpageritem_itemthree_one, null);
			HorzontialListView mHorzontialListView = (HorzontialListView) headHorzontialLayout
					.findViewById(R.id.android_widget_Scroller_HorizontalListView_four);
			ListView listView = (ListView) view
					.findViewById(R.id.home_activity_Itemthree_listView);
			//ʹ���ع����������ViewPager
			vpClassThree=new ViewPagerExceptHomeactivityUtil(this, view, headHorzontialLayout, mHorzontialListView, svAdapterThreePagerTop, lvAdapterThreePager
					,listView);
			
		}

		/**
		 * �Եڶ���viewPager��������
		 */
		private ViewPagerExceptHomeactivityUtil vpClassTwo;
		private void initeViewPagerTwo() {
			// TODO Auto-generated method stub
			View view = views.get(1);
			View headHorzontialLayout = li.inflate(
					R.layout.homeactivity_viewpageritem_itemtwo_one, null);
			HorzontialListView mHorzontialListView = (HorzontialListView) headHorzontialLayout
					.findViewById(R.id.android_widget_Scroller_HorizontalListView_three);
			ListView listView = (ListView) view
					.findViewById(R.id.home_activity_Itemtwo_listView);
			//ʹ���ع����������ViewPager
			vpClassTwo=new ViewPagerExceptHomeactivityUtil(this, view, headHorzontialLayout, mHorzontialListView, svAdapterOnePagerTop, lvAdapterTwoPager
					,listView);

		}

		/**
		 * �Ե�һ��viewPager��������
		 */
		private ViewPagerHomeUtil vpHomeClass;
		private void initeViewPagerOne() {
			//ʹ���ع����������ViewPager
			vpHomeClass=new ViewPagerHomeUtil(this, views, svAdapterPagerTwoTop, svAdapterOnePagerBottom,mHandler,TAG_PULL_REFRESH);
		}

		@Override
		public void handleMsg(Message msg) {
			// mViewPager.setCurrentItem(msg.what);
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			if (json==null) {
				System.out.println("json1111-------"+json+"----"+msg.what);
				return;
			}
			ArrayList<GoodsBean> listBean = JSONUtil.getGoodsJson(json);
			ScrollViewAdater po = null;

			if (msg.what == TAG_BARGIN) {
				//��һҳ����ĺ���listView
				svBeanPagerOneTop = listBean;
				svAdapterOnePagerTop.fillData(svBeanPagerOneTop);
				svAdapterOnePagerTop.notifyDataSetChanged();
				vpList.add(listBean);

				//�ڶ�ҳ����ĺ���listView
				svBeanPagerTwoTop = listBean;
				svAdapterPagerTwoTop.fillData(svBeanPagerTwoTop);
				svAdapterPagerTwoTop.notifyDataSetChanged();
				vpHomeList.add(listBean);

			} else if (msg.what == TAG_SQL_SELECT_HOT_SALE) {
				//��һҳ����ĺ���listView
				svBeanOnePagerBottom = listBean;
				svAdapterOnePagerBottom.fillData(svBeanOnePagerBottom);
				svAdapterOnePagerBottom.notifyDataSetChanged();
				vpHomeList.add(listBean);

				elasticScrollView=vpHomeClass.initeViewOneContrls(vpHomeList);
				
			} else if (msg.what == SQL_SELECT_PRAISE_GOODS) {
				//����ҳ����ĺ���listView
				svBeanPagerThreeTop = listBean;
				svAdapterThreePagerTop.fillData(svBeanPagerThreeTop);
				svAdapterThreePagerTop.notifyDataSetChanged();
				vpList.add(listBean);

				vpClassTwo.initeViewThreeContrls(vpList);
				vpClassThree.initeViewThreeContrls(vpList);
			} else if (msg.what == TAG_PULL_REFRESH) {
				elasticScrollView.onRefreshComplete();
			} 
		}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (event.KEYCODE_BACK==keyCode) {
				showDialog();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}

		public void showDialog() {
			AlertDialog.Builder ad=new Builder(this);
			ad.setTitle("�˳�");
			ad.setMessage("ȷ��Ҫ�˳���");
			ad.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					ExitApplication.getInstance().exit();
				}
			});
			ad.setNegativeButton("ȡ��", null);
			ad.show();
		}
		
	}
