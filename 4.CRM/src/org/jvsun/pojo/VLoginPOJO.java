package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class VLoginPOJO implements Serializable{
	private int loginId;//账号id
	private String account;//账号
	private String password;//密码
	private String userName;//角色
	private int userTape;//1位职工 0位客户
	private BigDecimal userId;//用户id
	
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserTape() {
		return userTape;
	}
	public void setUserTape(int userTape) {
		this.userTape = userTape;
	}
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public VLoginPOJO(int loginId,String account, String password, String userName,
			int userTape, BigDecimal userId) {
		super();
		this.loginId = loginId;
		this.account = account;
		this.password = password;
		this.userName = userName;
		this.userTape = userTape;
		this.userId = userId;
	}
	public VLoginPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
