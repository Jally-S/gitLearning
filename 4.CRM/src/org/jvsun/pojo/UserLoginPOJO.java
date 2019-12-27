package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
登录类
 */
public class UserLoginPOJO implements Serializable{

	private static final long serialVersionUID = -7756976328320783672L;
	private BigDecimal userId;//用户ID
	private String password;//密码
	private int role;//角色
	private int isDelete ;
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public UserLoginPOJO(BigDecimal userId, String password, int role,
			int isDelete) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.isDelete = isDelete;
	}
	
	public UserLoginPOJO(String password, int role,int isDelete) {
		super();
		this.password = password;
		this.role = role;
		this.isDelete = isDelete;
	}
	public UserLoginPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
