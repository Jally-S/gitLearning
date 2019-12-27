package com.hzit.dao;

import java.util.Date;
import java.util.List;

import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.User;
import com.hzit.entity.UserAddress;

public interface GoodsDao {
	public List<Product> getGoodsLevel(int start, int end,int type,int id);
	public int getCount(int type,int id);
	public List<News> getAllNews(int start,int end);
	public int getNewsCount();
	public Product getProductById(int id);
	public List<Product> queryByName(int start,int end,String keyWord);
	public int getProdctCount(String keyWord);
	public List<UserAddress> getAddressById(int entity);
	public void addAdress(String newAddress ,String newRemark,String date,User user);
	public void addOrders(int counts,User user,String seriaNumber,String data,String userAddress); 
	public UserAddress getAddressByAddressId(int addressId);
}
