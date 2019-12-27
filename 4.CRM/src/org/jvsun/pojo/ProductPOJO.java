package org.jvsun.pojo;

import java.io.Serializable;

/**
 * 产品类
 */
public class ProductPOJO implements Serializable{
	private static final long serialVersionUID = -5756922594978526911L;
	private int productId;//产品id
	private String productName;//产品名
	private String productDescribe;//产品描述
	private int productClass;//产品类别
	private int productInventory;//产品库存
	private double productPrice;//单价
	private int isDelete;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}
	public int getProductClass() {
		return productClass;
	}
	public void setProductClass(int productClass) {
		this.productClass = productClass;
	}
	public int getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public ProductPOJO(int productId, String productName,
			String productDescribe, int productClass, int productInventory,
			double productPrice, int isDelete) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescribe = productDescribe;
		this.productClass = productClass;
		this.productInventory = productInventory;
		this.productPrice = productPrice;
		this.isDelete = isDelete;
	}
	
	public ProductPOJO(String productName,
			String productDescribe, int productClass, int productInventory,
			double productPrice, int isDelete) {
		this.productName = productName;
		this.productDescribe = productDescribe;
		this.productClass = productClass;
		this.productInventory = productInventory;
		this.productPrice = productPrice;
		this.isDelete = isDelete;
	}
	public ProductPOJO(String productName,
			String productDescribe, int productClass, int productInventory,
			double productPrice) {
		this.productName = productName;
		this.productDescribe = productDescribe;
		this.productClass = productClass;
		this.productInventory = productInventory;
		this.productPrice = productPrice;
	}
	public ProductPOJO(int productId, String productName,
			String productDescribe, int productClass, int productInventory,double productPrice
			) {
		this.productId = productId;
		this.productName = productName;
		this.productDescribe = productDescribe;
		this.productClass = productClass;
		this.productInventory = productInventory;
		this.productPrice = productPrice;
	}
	public ProductPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductPOJO(String productName) {
		super();
		this.productName = productName;
	}
	
	
	
}
