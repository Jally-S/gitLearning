package com.anjoyo.jd.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.anjoyo.jd.R;
import com.anjoyo.jd.util.NetworkUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.Toast;

/*
 * �첽����ͼƬ �ڴ桢����˫�㻺��
 * xyl
 * 2013-12-15 21:20:50
 */
public class LoadImage {
	// SoftReference�������ã���Ϊ�˸��õ�Ϊ��ϵͳ���ձ������ڴ滺��ͼƬ
	private static HashMap<String, WeakReference<Bitmap>> imageCache = new HashMap<String, WeakReference<Bitmap>>();

	private Bitmap loadingImage, errorImage;
	private Context context;
	AnimationDrawable anim;

	public LoadImage(Context context) {
		super();
		this.context = context;

		errorImage = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.no_image);
	}

	public void loadDrawable(final String imageUrl, final ImageView imageView) {

		if (!NetworkUtil.isNetwork(context)) {
			imageView.setImageBitmap(errorImage);
			Toast.makeText(context, "��������ʧ�ܡ���", 500).show();
			return;
		}
		if (imageCache.containsKey(imageUrl)) {
			// �ӻ����л�ȡ
			WeakReference<Bitmap> weakReference = imageCache.get(imageUrl);
			Bitmap drawable = weakReference.get();
			if (drawable != null) {
				imageView.setImageBitmap(drawable);
				return;
			}
		}
		imageView.setImageDrawable(null);
		imageView.setBackgroundResource(R.drawable.animloading);
		anim = (AnimationDrawable) imageView.getBackground();
		// anim.start();
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {

				switch (message.what) {
				case 1:
					anim.start();
					break;
				case 2:
					// anim.setVisible(false, false);
					imageView.setImageBitmap(((Bitmap) message.obj));
					if (anim.isVisible()) {
						anim.stop();
						imageView.setBackgroundDrawable(null);
					}
					break;
				}
			}
		};
		// ������һ���µ��߳�����ͼƬ
		new Thread() {
			@Override
			public void run() {
				handler.sendEmptyMessage(1);
				Bitmap drawable = loadImageFromUrl(imageUrl);
				if (drawable == null) {
					drawable =errorImage;
				}
				imageCache.put(imageUrl, new WeakReference<Bitmap>(drawable));
				Message message = handler.obtainMessage();
				message.obj = drawable;
				message.what = 2;
				handler.sendMessage(message);
			}
		}.start();

	}

	private Bitmap loadImageFromUrl(String url) {
		Bitmap d =null;
		try {
			InputStream is = null;
			try {
				is = context
						.openFileInput(url.substring(url.lastIndexOf("/") + 1));
			} catch (Exception e) {
				System.out.println("û�л��档��");
			}
			if (is != null) {
				d = BitmapFactory.decodeStream(is);
				return d;
			}
			URL m = new URL(url);
			HttpURLConnection huc = (HttpURLConnection) m.openConnection();
			is = huc.getInputStream();
			// ���̻���ͼƬ
			FileOutputStream os = context.openFileOutput(
					url.substring(url.lastIndexOf("/") + 1),
					context.MODE_PRIVATE);
			byte[] b = new byte[100];
			int num = 0;
			while ((num = is.read(b)) > 0) {
				os.write(b, 0, num);
			}
			d = BitmapFactory.decodeStream(context.openFileInput(url
					.substring(url.lastIndexOf("/") + 1)));
			os.close();
			is.close();

		} catch (IOException e) {
			Log.d("xxx", e.toString() + "----���ӷ�����ʧ�ܡ���");

		}

		return d;
	}
}