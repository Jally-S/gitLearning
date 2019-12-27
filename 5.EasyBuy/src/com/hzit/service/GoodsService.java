package com.hzit.service;

import java.util.List;

import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.User;
import com.hzit.entity.UserAddress;

public interface GoodsService {
	public List<Product> getGoodsLevel(int page, int countPrePage,int type,int id);
	//得到商品应该展示的总页数
	public int getPages(int countPrePage,int type,int id );
	//得到商品分页前的总数
	public int getCounts(int type,int id );
	//得到所有的新闻咨询
	public List<News> getAllNews(int page ,int countPrePage2);
	//得到分页的总页数
	public int getNewsPages(int countPrePage2);
	//通过id查询对应的商品
	public Product getProductById(int id);
	//通过名称模糊查询商品
	public List<Product> queryByName(int page,int countPrePage,String keyWord);
	//得到产品的总页数
	public int getProductPages(String keyWord,int countPrePage2);
	//得到查询的所有条数
	public int getProductCounts(String keyWord);
	//通过顾客id查询所有的地址
	public List<UserAddress> getAddressById(int entity);
	//添加地址至数据库
	public void addAdress(String newAddress ,String newRemark);
	//添加订单
	public void addOrders(int counts,User user,String seriaNumber,String data,String userAddress); 
	//通过地址id查询对应的地址
	public UserAddress getAddressByAddressId(int addressId);
}
