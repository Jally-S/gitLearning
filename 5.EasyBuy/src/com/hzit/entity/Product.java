package com.hzit.entity;

import java.util.List;

/**
 * 商品实体类
 * 
 * @author ZhaoQu
 * 
 */
public class Product {
	private int id;
	private String name;
	private float price;
	private int stock;// 库存数量
	private int categoryLevel1Id;
	private int categoryLevel2Id;
	private int categoryLevel3Id;
	private String fileName;// 图片的名称
	private int isDelete;// 是否删除
	private List<OrederDetail> orederDetails;// 某个商品可以属于多个订单
	private int quantity;// 添加进购物车的商品数量

	public Product(int id, String name, float price, int stock,
			int categoryLevel1Id, int categoryLevel2Id, int categoryLevel3Id,
			String fileName, int isDelete, List<OrederDetail> orederDetails,
			int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.categoryLevel1Id = categoryLevel1Id;
		this.categoryLevel2Id = categoryLevel2Id;
		this.categoryLevel3Id = categoryLevel3Id;
		this.fileName = fileName;
		this.isDelete = isDelete;
		this.orederDetails = orederDetails;
		this.quantity = quantity;
	}

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCategoryLevel1Id() {
		return categoryLevel1Id;
	}

	public void setCategoryLevel1Id(int categoryLevel1Id) {
		this.categoryLevel1Id = categoryLevel1Id;
	}

	public int getCategoryLevel2Id() {
		return categoryLevel2Id;
	}

	public void setCategoryLevel2Id(int categoryLevel2Id) {
		this.categoryLevel2Id = categoryLevel2Id;
	}

	public int getCategoryLevel3Id() {
		return categoryLevel3Id;
	}

	public void setCategoryLevel3Id(int categoryLevel3Id) {
		this.categoryLevel3Id = categoryLevel3Id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public List<OrederDetail> getOrederDetails() {
		return orederDetails;
	}

	public void setOrederDetails(List<OrederDetail> orederDetails) {
		this.orederDetails = orederDetails;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", stock=" + stock + ", categoryLevel1Id=" + categoryLevel1Id
				+ ", categoryLevel2Id=" + categoryLevel2Id
				+ ", categoryLevel3Id=" + categoryLevel3Id + ", fileName="
				+ fileName + ", isDelete=" + isDelete + ", orederDetails="
				+ orederDetails + ", quantity=" + quantity + "]";
	}
}
