package com.anjoyo.jd.util;

import android.content.Context;
import android.widget.Toast;

public class NotificationUtil {

	/**
	 * 弹出土司内容的方法
	 * @param context
	 * @param text
	 */
	public static void notificationUtil(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
