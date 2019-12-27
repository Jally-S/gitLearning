package com.anjoyo.jd.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.widget.AdapterView.OnItemClickListener;

import com.anjoyo.jd.listener.RequestListener;
public class AsyncJDRunner {
	public static void request(final int tag, final String url,
			final JDParameters params, final String method,
			final RequestListener listener) {
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		es.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					String json = HttpManager.openUrl(url, method, params,params.getValue("profile_image"));
					
					listener.onComplete(tag, json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					listener.onException(e.toString()+"请求服务器连接异常....");
				}
			}
		});
	}

	
}
