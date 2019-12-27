package org.jvsun.pojo;

import java.io.Serializable;
import java.util.Date;

public class PhotoPOJO implements Serializable {
	/**
	 * 照片类
	 */
	private static final long serialVersionUID = 137938647425277838L;
	private int photoId;//照片id
	private String photoName;//照片名
	private String photoDescribe;//照片描述
	private int contractId;//对应合同ID
	private Date photoTime;//拍照时间
	private int isDelete;
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoDescribe() {
		return photoDescribe;
	}
	public void setPhotoDescribe(String photoDescribe) {
		this.photoDescribe = photoDescribe;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public Date getPhotoTime() {
		return photoTime;
	}
	public void setPhotoTime(Date photoTime) {
		this.photoTime = photoTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public PhotoPOJO(int photoId, String photoName,
			String photoDescribe, int contractId, Date photoTime,
			int isDelete) {
		super();
		this.photoId = photoId;
		this.photoName = photoName;
		this.photoDescribe = photoDescribe;
		this.contractId = contractId;
		this.photoTime = photoTime;
		this.isDelete = isDelete;
	}
	public PhotoPOJO(String photoName, int contractId) {
		this.photoName = photoName;
		this.contractId = contractId;
	}
	public PhotoPOJO(int photoId ,String photoName, int contractId) {
		this.photoId=photoId;
		this.photoName = photoName;
		this.contractId = contractId;
	}
	public PhotoPOJO( int photoId,String photoName) {
		this.photoId=photoId;
		this.photoName = photoName;
	}
	public PhotoPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhotoPOJO(String photoName, String photoDescribe,
			int contractId, Date photoTime, int isDelete) {
		super();
		this.photoName = photoName;
		this.photoDescribe = photoDescribe;
		this.contractId = contractId;
		this.photoTime = photoTime;
		this.isDelete = isDelete;
	} 
	
}
