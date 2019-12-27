package com.hzit.dao;

import java.util.List;

import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.ProductCategory;
import com.hzit.entity.User;

public interface UserDao {
	public User queryUser(String name,String password);
	public List<ProductCategory> queryFirstAll(int id);
	public List<Product> queryAll();
	public void add(User user);
	public List<News> queryAllNews();
}
