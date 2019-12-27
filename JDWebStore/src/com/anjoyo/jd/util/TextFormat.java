package com.anjoyo.jd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

public class TextFormat {
/**
 * 对TextView进行格式化
 * @param context
 * @param pattern
 */
	public static Spannable textFormat(String pattern){
		Pattern p=Pattern.compile("[0-9]+");
		Matcher m=p.matcher(pattern);
		
		Spannable s=new SpannableString(pattern);
		while(m.find()){
			s.setSpan(new ForegroundColorSpan(Color.RED), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//			Log.d("vvv", "s.setSpan--->m.group()"+m.group());
		}
		return s;
	}
}
