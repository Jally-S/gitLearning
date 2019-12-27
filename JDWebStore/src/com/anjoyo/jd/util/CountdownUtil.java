package com.anjoyo.jd.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.os.Handler;
import android.text.Spannable;
import android.widget.TextView;

public class CountdownUtil {
	private  int day = 8, hour = 8, minute = 8, second = 8;
	private TextView tv;
	public static HashMap<TextView, SoftReference<Thread>> hm=new HashMap<TextView, SoftReference<Thread>>();
	 Handler h = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				Spannable s = TextFormat.textFormat("还剩" + day + "天" + hour
						+ "时" + minute + "分" + second + "秒");
				tv.setText(s);
			}
		};
	};

	public void countdown(TextView tv) {
		if (hm.containsKey(tv)) {
			SoftReference<Thread> so=hm.get(tv);
			Thread thread=so.get();
			if (thread!=null&&thread.isAlive()) {	
				return;
			}
		}
		this.tv=tv;
		hm.put(tv, new SoftReference<Thread>(new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

					if (second > 0) {
						second -= 1;
					} else {
						if (minute > 0) {
							minute -= 1;
							second += 59;
						} else {
							if (hour > 0) {
								hour -= 1;
								minute += 59;
								second += 59;
							} else {
								if (day > 0) {

									day -= 1;
									hour += 23;
									minute += 59;
									second += 59;
								} else {
									day = 8;
									hour = 8;
									minute = 8;
									second = 7;
								}
							}
						}
					}
					h.sendEmptyMessage(1);
					try {
						Thread.sleep(990);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}));
		hm.get(tv).get().start();
		
	}
	
}
