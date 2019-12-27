package com.hzit.entity;

/**
 * 订单细节实体类
 * 
 * @author ZhaoQu
 * 
 */
public class OrederDetail {
	private int id;
	private Order oreder;// 订单细节属于订单
	private Product product;// 订单细节里面有具体的商品
	private int quantity;// 商品数量
	private float cost;// 商品单价乘以数量

	public OrederDetail() {
		super();
	}

	public OrederDetail(int id, Order oreder, Product product, int quantity,
			float cost) {
		super();
		this.id = id;
		this.oreder = oreder;
		this.product = product;
		this.quantity = quantity;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOreder() {
		return oreder;
	}

	public void setOreder(Order oreder) {
		this.oreder = oreder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
