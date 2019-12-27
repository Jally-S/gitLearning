package com.anjoyo.jd.bean;


public class SubCategoryBean {
	private int mSubCategoryGoodImageView;
	private String mSubCategoryGoodname;
	private String mSubCategoryDeatil;
	public SubCategoryBean(int mSubCategoryGoodImageView,
			String mSubCategoryGoodname, String mSubCategoryDeatil) {
		super();
		this.mSubCategoryGoodImageView = mSubCategoryGoodImageView;
		this.mSubCategoryGoodname = mSubCategoryGoodname;
		this.mSubCategoryDeatil = mSubCategoryDeatil;
	}
	public SubCategoryBean() {
		super();
	}
	public int getmSubCategoryGoodImageView() {
		return mSubCategoryGoodImageView;
	}
	public void setmSubCategoryGoodImageView(int mSubCategoryGoodImageView) {
		this.mSubCategoryGoodImageView = mSubCategoryGoodImageView;
	}
	public String getmSubCategoryGoodname() {
		return mSubCategoryGoodname;
	}
	public void setmSubCategoryGoodname(String mSubCategoryGoodname) {
		this.mSubCategoryGoodname = mSubCategoryGoodname;
	}
	public String getmSubCategoryDeatil() {
		return mSubCategoryDeatil;
	}
	public void setmSubCategoryDeatil(String mSubCategoryDeatil) {
		this.mSubCategoryDeatil = mSubCategoryDeatil;
	}
	

}
