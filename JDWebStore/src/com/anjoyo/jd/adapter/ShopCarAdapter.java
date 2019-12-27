package com.anjoyo.jd.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.anjoyo.jd.R;
import com.anjoyo.jd.activity.ShopCarActivity;
import com.anjoyo.jd.activity.ShopCarActivity.ZongJi;
import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.net.LoadImage;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopCarAdapter extends BaseAdapter {
	private List<GoodsBean> list = new ArrayList<GoodsBean>();
	static ArrayList<Integer> num = null;
	private Context context;
	private LayoutInflater inflater;
	double zm = 0.0;
	ZongJi zj;

	private HashMap<GoodsBean, Integer> checked = new HashMap<GoodsBean, Integer>();

	public ShopCarAdapter(Context context, List<GoodsBean> list2, ZongJi zj) {
		this.context = context;
		this.list = list2;
		inflater = LayoutInflater.from(context);
		this.zj = zj;
		num = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			num.add(1);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.shopcart_lv_item, null);
		}
		TextView promotionalInfo = (TextView) convertView
				.findViewById(R.id.jianjiashangp);
		final TextView zMoney = (TextView) convertView
				.findViewById(R.id.shopcart_item_zmoney);
		final CheckBox checkBox = (CheckBox) convertView
				.findViewById(R.id.shopcar_item_check);
		ImageView goods_image = (ImageView) convertView
				.findViewById(R.id.shopcart_lv_item_iv);
		TextView goods_name = (TextView) convertView
				.findViewById(R.id.shopcart_item_name);
		TextView goods_id = (TextView) convertView
				.findViewById(R.id.shopcart_item_number);
		TextView old_price = (TextView) convertView
				.findViewById(R.id.shopcart_item_singlemoney);
		Button jian = (Button) convertView
				.findViewById(R.id.shopcart_item_jiansl);
		Button jia = (Button) convertView
				.findViewById(R.id.shopcart_item_jiasl);
		final TextView shuLiang = (TextView) convertView
				.findViewById(R.id.shopcart_item_shuliangt);
		final GoodsBean bean = list.get(position);
		promotionalInfo.setText(bean.getGoods_promotion());
		LoadImage loadImage = new LoadImage(context);
		loadImage.loadDrawable(bean.getGoods_image(), goods_image);
		goods_name.setText(bean.getGoods_name());
		goods_id.setText("±àºÅ£º" + bean.getGoods_id());
		old_price.setText("£¤" + bean.getOld_price());
		double x = Integer.valueOf(shuLiang.getText().toString())
				* bean.getOld_price();
		zMoney.setText("£¤" + x);
		shuLiang.setText(num.get(position) + "");

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				// TODO Auto-generated method stub
				String str = zMoney.getText().toString();
				if (isChecked) {

					checked.put(bean,
							Integer.valueOf(shuLiang.getText().toString()));
				} else {
					checked.remove(bean);

				}
				initMoney();
				zj.deleteShopNumber(checked.keySet());
			}
		});
		if (checked.containsKey(list.get(position))) {
			checkBox.setChecked(true);
		} else {
			checkBox.setChecked(false);
		}
		jian.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count = Integer.parseInt(shuLiang.getText().toString());

				if (count > 1) {
					count = count - 1;
					shuLiang.setText(count + "");
					num.set(position, count);
					double c = count * bean.getOld_price();
					if (checkBox.isChecked()) {
						checked.put(bean, count);
						initMoney();
					}
					zMoney.setText("£¤" + c);
				}
			}
		});

		jia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int count = Integer.parseInt(shuLiang.getText().toString()) + 1;
				shuLiang.setText(count + "");
				num.set(position, count);
				double c = count * bean.getOld_price();
				zMoney.setText("£¤" + c);
				if (checkBox.isChecked()) {
					checked.put(bean, count);
					initMoney();
				}
			}

		});

		return convertView;
	}

	public void initMoney() {
		zm = 0.0;
		for (Entry<GoodsBean, Integer> entry : checked.entrySet()) {

			zm += entry.getKey().getOld_price() * entry.getValue();
		}
		zj.changeZongJi(zm);
	}

	public Map<GoodsBean, Integer> getAllChecked() {

		for (int i = 0; i < list.size(); i++) {
			checked.put(list.get(i), num.get(i));
			Log.d("aa", "aa====checkedid1 quan xuan=µÚÈý´Î=====" + checked.size()
					+ zm);
		}
		return checked;
	}

	public Map<GoodsBean, Integer> getNoChecked() {
		checked = new HashMap<GoodsBean, Integer>();
		return checked;
	}

	public void addData(List<GoodsBean> list1) {
		list = list1;
		for (int i = 0; i < list.size(); i++) {
			num.add(1);
		}
	}
}
