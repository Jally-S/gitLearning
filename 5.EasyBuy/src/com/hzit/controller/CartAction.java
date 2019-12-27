package com.hzit.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hzit.entity.Product;
import com.hzit.entity.User;
import com.hzit.entity.UserAddress;
import com.hzit.service.GoodsService;
import com.hzit.service.impl.GoodsServiceImpl;

/**
 * 购物车的Action
 * 
 * @author ZhaoQu
 * 
 */
public class CartAction {
	// 获取java web原生资源
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	private GoodsService goodsService = new GoodsServiceImpl();
	private int entityId;
	private int quantity;
	private Product product;
	//判断登陆成功后是否跳转到购物车页面
	private int flag;
	private List<UserAddress> listA;
	//订单编号
	private String number;
	private int counts;
	
	

	

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	//新订单的地址和备注
	private String  newAddress;
	private String newRemark;
	//当未添加新地址时我需要传地址id
	private int addressId;
	//标识是否选择了新地址
	private int flag2;
	
	public int getFlag2() {
		return flag2;
	}

	public void setFlag2(int flag2) {
		this.flag2 = flag2;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public String getNewRemark() {
		return newRemark;
	}

	public void setNewRemark(String newRemark) {
		this.newRemark = newRemark;
	}

	public List<UserAddress> getListA() {
		return listA;
	}

	public void setListA(List<UserAddress> listA) {
		this.listA = listA;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//添加购物车
	public String add() {
		// System.out.println(quantity);
		// System.out.println(entityId);
		// 此处取到商品的id和加入购物车的数量

		/**
		 * 1.购物车 Map<Integer,Product> cart 把购物车存入session中 
		 * 2.先获取购物车，判断是否是第一次访问
		 * 第一次访问：创建购物车，把商品的名称和数量加入到购物车，存入session中 * 
		 * 不是第一次访问 判断是否包含该商品，通过名称
		 * 如果包含，数量+1 存入session中 如果不包含，存入购物车，存入session中 
		 * 3.继续购物或者结算
		 */
		// 从session中获取购物车
		Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
		// 通过cart判断是否是第一次访问
		if (cart == null) {
			// 创建购物车
			cart = new HashMap<Integer, Product>();
			// 第一次访问
			product = goodsService.getProductById(entityId);
			// 将此时获取到的商品数量添加至Product对象
			product.setQuantity(quantity);
			// 将商品存入购物车
			cart.put(entityId, product);
			// 将购物车存入到session之中
			session.setAttribute("cart", cart);
			//计算此时的商品总计
			int count = 0;
			//获取到至的集合
			Collection<Product> products =  cart.values();
			//迭代循环遍历  计算出当前商品的总价格
			for (Product iter : products) {
				int quantity = iter.getQuantity();
				float price = iter.getPrice();
				count += quantity*price;
			}
			//将价格存入到session之中
			session.setAttribute("count", count);
			
		} else {
			// 不是第一次访问 判断是否包含该商品
			if (cart.containsKey(entityId)) {
				// 包含，取出数量，+1，存入购物车，存入seesion中
				// 取出
				Product product = cart.get(entityId);
				int quantit = product.getQuantity();
				quantit = quantit + quantity;
				product.setQuantity(quantit);
				// 购物车存入商品
				cart.put(entityId, product);
				// 将购物车存入到session之中
				session.setAttribute("cart", cart);
				//计算此时的商品总计
				int count = 0;
				//获取到至的集合
				Collection<Product> products =  cart.values();
				//迭代循环遍历  计算出当前商品的总价格
				for (Product iter : products) {
					int quantity = iter.getQuantity();
					float price = iter.getPrice();
					count += quantity*price;
				}
				//将价格存入到session之中
				session.setAttribute("count", count);
			} else {
				// 不包含该商品
				product = goodsService.getProductById(entityId);
				product.setQuantity(quantity);
				// 将该商品添加进购物车
				cart.put(entityId, product);
				// 将购物车添加至session之中
				session.setAttribute("cart", cart);
				//计算此时的商品总计
				int count = 0;
				//获取到至的集合
				Collection<Product> products =  cart.values();
				//迭代循环遍历  计算出当前商品的总价格
				for (Product iter : products) {
					int quantity = iter.getQuantity();
					float price = iter.getPrice();
					count += quantity*price;
				}
				//将价格存入到session之中
				session.setAttribute("count", count);
			}
		}
		return "add_ok";
	}

	// 删除购物车对应的商品
	public void deleteCart() {
		// 从session中获取购物车
		Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
		cart.remove(entityId);
		//计算此时的商品总计
		int count = 0;
		//获取到至的集合
		Collection<Product> products =  cart.values();
		//迭代循环遍历  计算出当前商品的总价格
		for (Product iter : products) {
			int quantity = iter.getQuantity();
			float price = iter.getPrice();
			count += quantity*price;
		}
		//将价格存入到session之中
		session.setAttribute("count", count);
		try {
			response.getWriter().print(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//持久化商品数量到购物车
	public void addQuantity(){
		//获取购物车
		Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
		product = cart.get(entityId);
		product.setQuantity(quantity);
		//覆盖原有的Product对象
		cart.put(entityId, product);
		//计算此时的商品总计
		int count = 0;
		//获取到至的集合
		Collection<Product> products =  cart.values();
		//迭代循环遍历  计算出当前商品的总价格
		for (Product iter : products) {
			int quantity = iter.getQuantity();
			float price = iter.getPrice();
			count += quantity*price;
		}
		//将价格存入到session之中
		session.setAttribute("count", count);
		try {
			response.getWriter().print(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	// 添加订单信息至Order表中
	public String addOrder() {
		//确认结算的时候必须判断该用户是否登陆
		User user = (User) session.getAttribute("user");

		//判断是否为空  
		if (user != null) {
			//已登录  
			/*
			 * 1  展示订单信息
			 * 2 展示用户信息
			 * 3 展示已有收货地址
			 * 4 填写收获地址
			 * 
			 */
			//1  展示购物车信息  已存储在session之中 
			//2 展示用户信息  已存储在session之中 
			//3 展示已有收货地址   通过顾客id查询出所有的地址
			listA = goodsService.getAddressById(user.getId());
			
		}else {
			//未登录
			//通过配置文件重定向到登陆页面进行登陆
			flag = 1;
			//通过配置文件跳转到登录界面
			return "login_fail";
		}
		return "addOrder_ok";
	}
	
	//当用户选择的是新地址时执行的操作
	public String addAdress(){
		if(flag2==0){
			//添加了新地址
			//将地址添持久化到数据库
			goodsService.addAdress(newAddress, newRemark);
		}
		
		if(flag2 == 1){
			//选择原有的地址
			//通过id查询原有地址
			UserAddress address = goodsService.getAddressByAddressId(addressId);
			newAddress = address.getAddress();
		}
		
		//获取到总共的价格
		counts = (int) session.getAttribute("count");
		//获取user对象
		User user = (User) session.getAttribute("user");
		//获取当前系统时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = sdf.format(date);
		//生成订单序列号
		 String seriaNumber = null;
		try {  
            StringBuffer result = new StringBuffer();  
            for(int i=0;i<32;i++) {  
                result.append(Integer.toHexString(new Random().nextInt(16)));  
            }  
            seriaNumber =  result.toString().toUpperCase();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		//订单持久化
		goodsService.addOrders(counts, user, seriaNumber, data, newAddress);
		
		//将数据传输到订单页面
		//订单总价格存储在session之中
		//订单编号
		number = seriaNumber;
		Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
//		//清空购物车
		session.removeAttribute("cart");
		session.removeAttribute("count");
		
		//将其重新存储进session之中
		session.setAttribute("carts", cart);
		
		return "addOrders_ok";
	}

	//通过用户的id获取用户的订单
	public String getOrderByUserId(){
		
		
		
		return "getOrderByUserId_ok";
	}
	
	
	
}
