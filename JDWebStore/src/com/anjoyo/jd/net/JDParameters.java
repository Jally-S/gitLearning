package com.anjoyo.jd.net;

	import android.text.TextUtils;
	import java.util.ArrayList;
	public class JDParameters {

	  private ArrayList<String> mKeys = new ArrayList<String>();
	  private ArrayList<String> mValues = new ArrayList<String>();

	  /**
	   * ���String key,String value
	   * @param key
	   * @param value
	   */
	  public void add(String key, String value)
	  {
	    if ((!TextUtils.isEmpty(key)) && (!TextUtils.isEmpty(value))) {
	      this.mKeys.add(key);
	      this.mValues.add(value);
	    }
	  }

	  /**
	   * ���String key,<String>int value
	   * @param key
	   * @param value
	   */
	  public void add(String key, int value)
	  {
	    this.mKeys.add(key);
	    this.mValues.add(String.valueOf(value));
	  }
	  
	  /**
	   * ���String key,<String>Long value
	   * @param key
	   * @param value
	   */
	  public void add(String key, long value) {
	    this.mKeys.add(key);
	    this.mValues.add(String.valueOf(value));
	  }


	  /**
	   * ͨ������key��ArrayLIst��key ��ȡ ��Ӧ�� index
	   * @param key
	   * @return
	   */
	  private int getLocation(String key)
	  {
	    if (this.mKeys.contains(key)) {
	      return this.mKeys.indexOf(key);
	    }
	    return -1;
	  }

	  /**
	   *  ͨ������key��ArrayLIst��index ��ȡ ��Ӧ�� key
	   * @param location
	   * @return
	   */
	  public String getKey(int location) {
	    if ((location >= 0) && (location < this.mKeys.size())) {
	      return (String)this.mKeys.get(location);
	    }
	    return "";
	  }

	  /**
	   * ͨ��String key��ArrayLIst ��ȡValue��ArrayLIst��Ӧ ��String value
	   * @param key
	   * @return
	   */
	  public String getValue(String key)
	  {
	    int index = getLocation(key);
	    if ((index >= 0) && (index < this.mKeys.size())) {
	      return (String)this.mValues.get(index);
	    }

	    return null;
	  }

	  /**
	   * ͨ��key��ArrayLIst�� index ��ȡ value ArrayList�� String vaule
	   * @param location
	   * @return
	   */
	  public String getValue(int location)
	  {
	    if ((location >= 0) && (location < this.mKeys.size())) {
	      String rlt = (String)this.mValues.get(location);
	      return rlt;
	    }

	    return null;
	  }

	  /**
	   * ��ȡkey ��ӦArrayList��size()
	   * @return
	   */
	  public int size()
	  {
	    return this.mKeys.size();
	  }
	  
	  public void addAll(JDParameters parameters) {
	    for (int i = 0; i < parameters.size(); i++)
	      add(parameters.getKey(i), parameters.getValue(i));
	  }

	  /**
	   * ��� ArrayList �е���������
	   */
	  public void clear()
	  {
	    this.mKeys.clear();
	    this.mValues.clear();
	  }
	}

