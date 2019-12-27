package com.anjoyo.jd.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.os.Bundle;
import android.util.Log;

import com.anjoyo.jd.bean.GoodsBean;
import com.anjoyo.jd.bean.User;

public class JSONUtil {
	public static ArrayList<String> getVersion(String json) {
		ArrayList<String> al = new ArrayList<String>();
		try {
			JSONObject js = new JSONObject(json);
			al.add(js.getString("versioncode"));
			al.add(js.getString("versionname"));
			al.add(js.getString("location"));
			al.add(js.getString("versioncontext"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;

	}

	public static int getUpVersion(String json) {
		int state = 0;
		try {
			JSONObject js = new JSONObject(json);
			state = js.getInt("state");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return state;

	}

	/**
	 * { “state”:”001/012”,“goods”:[{“goods_id”:”001”,“goods_image_url”:”iamges/
	 * xxxxxx.jpg”,”goods_name”:”LP 2013冬季新工装大毛领羽绒服厚中长款修身羽绒服女
	 * 314Y056拍下仅需479元”,”new_price
	 * ”:”50”,”old_price”:”100”,“praise_scale”：“90%”，”
	 * scales_volume“：”1000“,”create_time”:”2013.12.11 20:45:09”,”goods_location
	 * “:”北京朝阳区”,”goods_promotion”:”降已优惠￥10.00满减满299.0立减30.0”...}...] }001:请求成功
	 * 012：没有匹配商品
	 * 
	 * @param json
	 * @return
	 */
	public static ArrayList<GoodsBean> getGoodsJson(String json) {
		ArrayList<GoodsBean> goodsBeans = new ArrayList<GoodsBean>();

		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("goods");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArray.get(i);
				int goods_id = jsonObj.getInt("goods_id");
				String goods_image = jsonObj.getString("goods_image");
				String goods_name = jsonObj.getString("goods_name");
				double new_price = jsonObj.getDouble("new_price");
				double old_price = jsonObj.getDouble("old_price");
				double praise_scale = jsonObj.getDouble("praise_scale");
				int scales_volume = jsonObj.getInt("scales_volume");
				String create_time = jsonObj.getString("create_time");
				String goods_promotion = jsonObj.getString("goods_promotion");
				String goods_location = jsonObj.getString("goods_location");
				GoodsBean goods = new GoodsBean(goods_id, goods_image,
						goods_name, new_price, old_price, praise_scale,
						scales_volume, create_time, goods_promotion,
						goods_location);
				goodsBeans.add(goods);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("GGG", e.toString());
		}
		return goodsBeans;
	}

	public static HashMap<String, HashMap<String, HashMap<String, ArrayList<GoodsBean>>>> getGoodsJsonAll(
			String json) {

		HashMap<String, HashMap<String, HashMap<String, ArrayList<GoodsBean>>>> goodsBean = new HashMap<String, HashMap<String, HashMap<String, ArrayList<GoodsBean>>>>();

		try {
			JSONObject jsonObject = new JSONObject(json);

			if (jsonObject.getInt("state") != 1) {
				return null;
			}
			HashMap<String, HashMap<String, ArrayList<GoodsBean>>> hm1 = new HashMap<String, HashMap<String, ArrayList<GoodsBean>>>();
			String category = jsonObject.getString("category");
			JSONArray jsonArray = jsonObject.getJSONArray("goods");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArray.get(i);
				String sub_category = jsonObj.getString("sub_category");
				JSONArray jsonArray1 = jsonObj.getJSONArray("content");
				HashMap<String, ArrayList<GoodsBean>> hm2 = new HashMap<String, ArrayList<GoodsBean>>();
				for (int j = 0; j < jsonArray1.length(); j++) {
					JSONObject jsonObj1 = (JSONObject) jsonArray1.get(i);
					String s_sub_category = jsonObj1
							.getString("s_sub_category");
					JSONArray jsonArray2 = jsonObj1.getJSONArray("s_content");
					ArrayList<GoodsBean> al2 = new ArrayList<GoodsBean>();
					for (int k = 0; k < jsonArray2.length(); k++) {
						JSONObject jsonObj2 = (JSONObject) jsonArray2.get(i);

						int goods_id = jsonObj2.getInt("goods_id");
						String goods_image = jsonObj2.getString("goods_image");
						String goods_name = jsonObj2.getString("goods_name");
						double new_price = jsonObj2.getDouble("new_price");
						double old_price = jsonObj2.getDouble("old_price");
						double praise_scale = jsonObj2
								.getDouble("praise_scale");
						int scales_volume = jsonObj2.getInt("scales_volume");
						String create_time = jsonObj2.getString("create_time");
						String goods_promotion = jsonObj2
								.getString("goods_promotion");
						String goods_location = jsonObj2
								.getString("goods_location");
						GoodsBean goods = new GoodsBean(goods_id, goods_image,
								goods_name, new_price, old_price, praise_scale,
								scales_volume, create_time, goods_promotion,
								goods_location, s_sub_category, sub_category,
								category);
						al2.add(goods);
					}
					hm2.put(s_sub_category, al2);
				}
				hm1.put(sub_category, hm2);
			}
			goodsBean.put(category, hm1);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("GGG", e.toString());
		}

		return goodsBean;
	}

	public static HashMap<String, ArrayList<String>> getGoodsCategoryJson(
			String json) {

		HashMap<String, ArrayList<String>> categoryhm = new HashMap<String, ArrayList<String>>();

		try {
			JSONObject jsonObject = new JSONObject(json);

			if (jsonObject.getInt("state") != 1) {
				Log.d("xxx", "获取json失败");
				return null;
			}
			ArrayList<String> al = new ArrayList<String>();
			String sub_category = jsonObject.getString("sub_category");
			JSONArray jsonArray = jsonObject.getJSONArray("content");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArray.get(i);
				String s_sub_category = jsonObj.getString("s_sub_category");

				al.add(s_sub_category);
			}
			categoryhm.put(sub_category, al);
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("GGG", e.toString());
		}

		return categoryhm;
	}

	
	
	public static int parseRegister(String json) {
		int state = -1;
		try {
			JSONObject jsonObject = new JSONObject(json);
			state = jsonObject.getInt("state");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}

	/**
	 * 保存用户名id
	 * 
	 * @param json
	 */
	public static User parseLogin(String json) {
		Bundle bundle = new Bundle();
		User user = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			int state = jsonObject.getInt("state");
			if (state == 1) {

				int user_id = jsonObject.getInt("user_id");
				String username = jsonObject.getString("username");
				String profile_image = "";
				if (jsonObject.has("profile_image")) {

					profile_image = jsonObject.getString("profile_image");
				}
				float money = (float) jsonObject.getDouble("money");
				user = new User(user_id, username, profile_image, money);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

		public static int parseDeletShopCar(String json) {
		int state = 0;
		try {
			JSONObject jsonObject = new JSONObject(json);
			state = (Integer) jsonObject.get("state");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
}
