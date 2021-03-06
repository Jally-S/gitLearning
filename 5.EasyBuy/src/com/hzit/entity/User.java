package com.hzit.entity;

import java.util.List;

/**
 * 管理员实体类
 * 
 * @author ZhaoQu
 * 
 */
public class User {
	private int id;
	private String loginName;
	private String userName;
	private String password;
	private int sex;
	private String identityCode;
	private String email;
	private String mobile;
	private int type;// 是否是管理员
	private String fileName;// 照片地址
	private List<Order> order;// 订单集合
	private List<UserAddress> userAddresses;// 地址集合

	public User() {
		super();
	}

	public User(int id, String loginName, String userName, String password,
			int sex, String identityCode, String email, String mobile,
			int type, String fileName, List<Order> order,
			List<UserAddress> userAddresses) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
		this.fileName = fileName;
		this.order = order;
		this.userAddresses = userAddresses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(List<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", userName="
				+ userName + ", password=" + password + ", sex=" + sex
				+ ", identityCode=" + identityCode + ", email=" + email
				+ ", mobile=" + mobile + ", type=" + type + ", fileName="
				+ fileName + ", order=" + order + ", userAddresses="
				+ userAddresses + "]";
	}

}
