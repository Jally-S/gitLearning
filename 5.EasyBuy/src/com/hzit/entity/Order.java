package com.hzit.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * 
 * @author ZhaoQu
 * 
 */
public class Order {
	private int id;
	private int userId;
	private String userAddress;
	private String createTime;// 订单创建时间
	private float cost;// 订单消费总额
	private String serialNumber;// 订单序列号
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, int userId, String userAddress, String createTime,
			float cost, String serialNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.userAddress = userAddress;
		this.createTime = createTime;
		this.cost = cost;
		this.serialNumber = serialNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	
}
