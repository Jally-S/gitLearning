package com.anjoyo.jd.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.james.mime4j.message.Entity;

import com.anjoyo.jd.R;
import com.anjoyo.jd.constant.Constant;
import com.anjoyo.jd.net.HttpManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

/**
 * 
 * 版本检测，自动更新
 * 
 * 
 * 1.通过Url检测更新 2.下载并安装更新
 * 
 * 
 */

public class VersionUpdateUtil {

	private Context context;
	public int versionCode = 0;
	public String versionName = "";
	private static final String TAG = "AutoUpdate";

	private String currentPath = "";
	private String strURL = "";
	private String versionContext;
	private ProgressDialog dialog;
	private ArrayList<String> versionList;

	/**
	 * 
	 * 构造方法，获得当前版本信息
	 * 
	 * 
	 * @param activity
	 */

	public VersionUpdateUtil(Context context) {

		this.context = context;

		// 获得当前版本

		getCurrentVersion();

	}

	/**
	 * 
	 * 检测更新
	 */

	public void check(String json) {

		// 检测网络

		if (!NetworkUtil.isNetwork(context)) {
			Toast.makeText(context, "网络异常。。检查网络再检测更新", 500).show();
			return;

		}

		versionList = JSONUtil.getVersion(json);
		if (!(versionList.size()==4)) {
			Toast.makeText(context, "服务器版本更新异常。。", 500).show();
			return;
		}
		strURL = versionList.get(2);
		versionContext = versionList.get(3);
		if ((Integer.valueOf(versionList.get(0))) > versionCode) {

			showUpdateDialog();

		} else if (Integer.valueOf(versionList.get(0)) < versionCode) {
			showUploadDialog();
		} else if (Integer.valueOf(versionList.get(0)) == versionCode) {
			Toast.makeText(context, "当前已是最新版本。。", 500).show();
		}

	}

	public void showUploadDialog() {

		AlertDialog alert = new AlertDialog.Builder(context)

		.setTitle("当前是最新版本是否上传到服务器？").setMessage(versionContext)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						new Thread(new Runnable() {
							@Override
							public void run() {

								// TODO Auto-generated method stub
								doUploadFile();
							}

						}).start();

						showWaitUpDialog();

					}

				})

				.setNegativeButton("取消", null).show();

	}

	private void doUploadFile() {
		// TODO Auto-generated method stub
		HttpPost post = new HttpPost(Constant.url+"uploadversion");
		MultipartEntity entity=new MultipartEntity();
		ContentBody contentBody=new FileBody(new File(currentPath));
//		entity.addPart("", new StringBody(""));
		entity.addPart(versionCode+"", contentBody);
		post.setEntity(entity);
		HttpClient client=new DefaultHttpClient();
		String result="";
		try {
			HttpResponse response=client.execute(post);
			result=HttpManager.readHttpResponse(response);
			int state=JSONUtil.getUpVersion(result);
			if (state==1) {
				Log.d(TAG, "上传完成");
				dialog.cancel();
			}
			else
			{Log.d(TAG, "上传失败");
				dialog.cancel();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(TAG, "上传失败");
			dialog.cancel();
		} 
		
	}

	public void showUpdateDialog() {

		AlertDialog alert = new AlertDialog.Builder(context)

		.setTitle("发现SQ_JD最新版本").setMessage(versionContext)
				.setPositiveButton("现在升级", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						new Thread(new Runnable() {
							@Override
							public void run() {

								// TODO Auto-generated method stub
								doDownloadFile(strURL);
							}

						}).start();

						showWaitDialog();

					}

				})

				.setNegativeButton("取消", null).show();

	}

	public void showWaitDialog() {

		dialog = new ProgressDialog(context);
		dialog.setIcon(R.drawable.android_jd_notification);
		dialog.setTitle("更新版本");
		dialog.setMessage("正在更新，请稍候...");
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMax(100);
		dialog.setCancelable(true);
		dialog.show();
	}
	public void showWaitUpDialog() {
		
		dialog = new ProgressDialog(context);
		dialog.setIcon(R.drawable.android_jd_notification);
		dialog.setTitle("更新版本");
		dialog.setMessage("正在上传，请稍候...");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setCancelable(true);
		dialog.show();
	}

	public void getCurrentVersion() {

		try {

			// 获取应用包信息

			PackageInfo info = context.getPackageManager().getPackageInfo(

			context.getPackageName(), 0);
			
			this.versionCode = info.versionCode;
			this.currentPath=context.getFilesDir().getAbsolutePath();
			
			System.out.println(currentPath+"---------");
			this.versionName = info.versionName;

		} catch (NameNotFoundException e) {

			e.printStackTrace();

		}

	}

	private void doDownloadFile(String strPath) {

		Log.i(TAG, "getDataSource()");

		// 判断strPath是否为网络地址

		if (!URLUtil.isNetworkUrl(strPath)) {
			
			Log.d(TAG,"服务器地址错误。。");
			dialog.cancel();

		} else {

			URL myURL;
			try {
				myURL = new URL(strPath);
				URLConnection conn = myURL.openConnection();
				InputStream is = conn.getInputStream();
				if (is == null) {
					throw new RuntimeException("stream is null");
				}
				File myTempFile = new File("/mnt/sdcard/"
						+ strURL.substring(strURL.lastIndexOf("/")));
				FileOutputStream fos = new FileOutputStream(myTempFile);
				byte buf[] = new byte[1024];
				int numread = 0;
				int count = conn.getContentLength();
				dialog.setMax(count);
				int tem=0;
				while ((numread = is.read(buf)) > 0) {
					fos.write(buf, 0, numread);
					tem=tem+numread;
					dialog.setProgress(tem);
				}
				Log.i(TAG, "getDataSource() Download ok...");
				dialog.cancel();
				openFile(myTempFile);
				is.close();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				Toast.makeText(context, e.toString(), 500).show();
				dialog.cancel();
			}
		}

	}

	/**
	 * 
	 * 打开文件进行安装
	 * 
	 * 
	 * @param f
	 */

	private void openFile(File f) {

		Intent intent = new Intent();

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		intent.setAction(android.content.Intent.ACTION_VIEW);

		// 打开各种类型文件

		intent.setDataAndType(Uri.fromFile(f),
				"application/vnd.android.package-archive");

		// 安装

		context.startActivity(intent);

	}

	/**
	 * 
	 * 删除临时路径里的安装包
	 */

	public void delFile() {

		//
		// File myFile = new File(currentTempFilePath);
		//
		// if (myFile.exists()) {
		//
		// myFile.delete();
		//
		// }

	}

	/**
	 * 
	 * 获得下载文件的类型
	 * 
	 * 
	 * @param f
	 * 
	 *            文件名称
	 * 
	 * @return 文件类型
	 */

	private String getMIMEType(File f) {

		String type = "";

		// 获得文件名称

		String fName = f.getName();

		// 获得文件扩展名

		String end = fName

		.substring(fName.lastIndexOf(".") + 1, fName.length())

		.toLowerCase();

		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")

		|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {

			type = "audio";

		} else if (end.equals("3gp") || end.equals("mp4")) {

			type = "video";

		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")

		|| end.equals("jpeg") || end.equals("bmp")) {

			type = "image";

		} else if (end.equals("apk")) {

			type = "application/vnd.android.package-archive";

		} else {

			type = "*";

		}

		if (end.equals("apk")) {

		} else {

			type += "/*";

		}

		return type;

	}

}
