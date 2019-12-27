package com.hzit.entity;

import java.util.Date;
import java.util.List;

/**
 * 用户地址实体类
 * 
 * @author ZhaoQu
 * 
 */
public class UserAddress {
	private int id;
	private String userId;// user的id
	private String address;// 地址
	private String createTime;// 创建时间
	private int isDefault;
	private String remark;
	public UserAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAddress(int id, String userId, String address,
			String createTime, int isDefault, String remark) {
		super();
		this.id = id;
		this.userId = userId;
		this.address = address;
		this.createTime = createTime;
		this.isDefault = isDefault;
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", userId=" + userId + ", address="
				+ address + ", createTime=" + createTime + ", isDefault="
				+ isDefault + ", remark=" + remark + "]";
	}
	
	

}
