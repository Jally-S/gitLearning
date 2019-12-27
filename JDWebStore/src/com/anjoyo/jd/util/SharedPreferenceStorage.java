package com.anjoyo.jd.util;

import org.json.JSONObject;

import com.anjoyo.jd.bean.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

public class SharedPreferenceStorage {

	/*
	 * 存储用户名user_id
	 */
	public static void saveLoginUserId(Context context, User user) {
		SharedPreferences sp = context.getSharedPreferences("user",
				context.MODE_PRIVATE);
		Editor et = sp.edit();
		et.putInt("user_id", user.getUser_id());
		et.putString("username", user.getUsername());
		et.putString("profile_image", user.getProfile_image());
		if (user.getMoney()!=null) {
			
			et.putFloat("money",new Float(user.getMoney()));
		}
		else {
			et.putFloat("money",new Float(0));
		}
		et.commit();
	}
	public static void saveLoginUserImage(Context context, String imgpath) {
		SharedPreferences sp = context.getSharedPreferences("user",
				context.MODE_PRIVATE);
		Editor et = sp.edit();
		et.putString("profile_image", imgpath);
		et.commit();
	}

	/*
	 * 取用户名user_id
	 */
	public static User getLoginUser(Context context) {
		SharedPreferences sp = context.getSharedPreferences("user",
				context.MODE_PRIVATE);
		if (sp.getString("username",null)!=null) {
			
			return new User(sp.getInt("user_id",0),sp.getString("username",null), sp.getString("profile_image",null), sp.getFloat("money",new Float(0.0)));
		}
		else
			return null;
	}

}
