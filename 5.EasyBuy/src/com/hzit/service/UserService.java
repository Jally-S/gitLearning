package com.hzit.service;

import java.util.List;

import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.ProductCategory;
import com.hzit.entity.User;

public interface UserService {
	//查询用户  看是否存在   从而登陆
	public User queryUser(String name,String password);
	//查询上品分类
	public List<ProductCategory> queryFirstAll(int id);
	//查询具体的商品
	public List<Product> queryAll();
	//向数据库增加成员
	public void add(User user);
	//获取新闻
	public List<News> queryAllNews();
}
