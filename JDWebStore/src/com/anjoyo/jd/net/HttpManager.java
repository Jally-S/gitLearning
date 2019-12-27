package com.anjoyo.jd.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
/*import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;*/
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpManager {

	private final static String UTF_8 = "gbk";
	private final static int TIMEOUT_CONNECTION = 30000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int TIMEOUT_CONN = 20000;
	private final static int MAX_CONNECTION_COUNT = 400;

	static HttpClient getHttpCilent() {
		HttpParams params = new BasicHttpParams();

		// �����ַ���
		HttpProtocolParams.setContentCharset(params, UTF_8);

		// ����httpЭ��汾
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

		// �����Ƿ���֤���� ��Ϊtrue�ᵼ��������������
		HttpProtocolParams.setUseExpectContinue(params, false);

		// �������ӳ�ȡ���ӳ�ʱʱ��
		ConnManagerParams.setTimeout(params, TIMEOUT_CONN);

		// �������������
		ConnPerRouteBean cRouteBean = new ConnPerRouteBean(MAX_CONNECTION_COUNT);
		ConnManagerParams.setMaxConnectionsPerRoute(params, cRouteBean);

		// �������ӳ�ʱ
		HttpConnectionParams.setConnectionTimeout(params, TIMEOUT_CONNECTION);

		// ���ö�ȡ���ݳ�ʱ
		HttpConnectionParams.setSoTimeout(params, TIMEOUT_SOCKET);

		// ��������֧��http��https����Э��
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		// ʹ���̰߳�ȫ�Ĺ�����httpClient
		ClientConnectionManager ccm = new ThreadSafeClientConnManager(params,
				schemeRegistry);
		DefaultHttpClient hr = new DefaultHttpClient(ccm, params);
		return hr;

	}

	public static String openUrl(String url, String method, JDParameters params,String profile_image)
			throws Exception {
		String result = "";

		HttpClient client = getHttpCilent();
		HttpUriRequest request = null;
		ByteArrayOutputStream bos = null;
		
		if(profile_image!=null){
			HttpPost post = new HttpPost(url);
			MultipartEntity entity=new MultipartEntity();
			ContentBody contentBody=new FileBody(new File(profile_image));
			entity.addPart(params.getValue("user_id")+".png", contentBody);
			post.setEntity(entity);
			request=post;
		}
		else if ("GET".equals(method)) {
			url = url + "?" + encodeUrl(params);
			HttpGet get = new HttpGet(url);
			request = get;
		} else if ("POST".equals(method)) {
			
			HttpPost post = new HttpPost(url);
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			for (int i = 0; i < params.size(); i++) {

				parameters.add(new BasicNameValuePair(params.getKey(i), params
						.getValue(i)));
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			request=post;
		}
		HttpResponse response = client.execute(request);
		
		result = readHttpResponse(response);
		
		return result;

	}

	public static String readHttpResponse(HttpResponse response) throws Exception {
		String result = "";
		HttpEntity entity = response.getEntity();
		result=EntityUtils.toString(entity);
		return result;
	}

	private static String encodeUrl(JDParameters parameters) {
		if (parameters == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (int loc = 0; loc < parameters.size(); loc++) {
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			String _key = parameters.getKey(loc);
			String _value = parameters.getValue(_key);
			if (_value == null) {
				Log.i("httpmanage-------encodeUrl", "key:" + _key + " 's value is null");
			} else {
				sb.append(URLEncoder.encode(parameters.getKey(loc)) + "="
						+ URLEncoder.encode(parameters.getValue(loc)));
			}
			Log.i("httpmanage-------encodeUrl", sb.toString());
		}
		return sb.toString();
	}

}