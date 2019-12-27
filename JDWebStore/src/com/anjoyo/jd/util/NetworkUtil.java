package com.anjoyo.jd.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

	/**
	 * 判断网络是否连接，成功连接返回true；
	 * @param context
	 * @return
	 */
	public static boolean isNetwork(Context context){
		ConnectivityManager conn=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info=conn.getActiveNetworkInfo();
		if(info!=null&&info.isAvailable())
			return true;
		return false;
	}
}
