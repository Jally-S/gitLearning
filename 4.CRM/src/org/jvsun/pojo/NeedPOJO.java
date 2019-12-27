package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户需求类
 *
 */
public class NeedPOJO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3983702303350403157L;
	private BigDecimal needId;
	private BigDecimal customerId;
	private String nhobby;
	private String productName;
	private String customerName;
	private int isDel;
	
	public BigDecimal getNeedId() {
		return needId;
	}
	public void setNeedId(BigDecimal needId) {
		this.needId = needId;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public String getNhobby() {
		return nhobby;
	}
	public void setNhobby(String nhobby) {
		this.nhobby = nhobby;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public NeedPOJO(BigDecimal needId, BigDecimal customerId, String nhobby,
			String productName, int isDel) {
		super();
		this.needId = needId;
		this.customerId = customerId;
		this.nhobby = nhobby;
		this.productName = productName;
		this.isDel = isDel;
	}
	public NeedPOJO (BigDecimal customerId, String nhobby,
			String productName) {
		this.customerId = customerId;
		this.nhobby = nhobby;
		this.productName = productName;
	}
	public NeedPOJO ( String nhobby,
			String productName,BigDecimal needId) {
		this.nhobby = nhobby;
		this.productName = productName;
		this.needId = needId;
	}
	public NeedPOJO(BigDecimal needId, String customerName, String nhobby,
			String productName, int isDel) {
		super();
		this.needId = needId;
		this.customerName = customerName;
		this.nhobby = nhobby;
		this.productName = productName;
		this.isDel = isDel;
	}
	public NeedPOJO(BigDecimal customerId, String nhobby,
			String productName, int isDel) {
		this.customerId = customerId;
		this.nhobby = nhobby;
		this.productName = productName;
		this.isDel = isDel;
	}
	public NeedPOJO(BigDecimal needId,String customerName, String nhobby,
			String productName) {
		this.needId=needId;
		this.setCustomerName(customerName);
		this.nhobby = nhobby;
		this.productName = productName;
	}
	public NeedPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "NeedPOJO [needId=" + needId + ", customerId=" + customerId
				+ ", nhobby=" + nhobby + ", productName=" + productName
				+ ", customerName=" + customerName + ", isDel=" + isDel + "]";
	}
	
}
