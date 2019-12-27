package com.hzit.service.impl;

import java.util.List;

import com.hzit.dao.UserDao;
import com.hzit.dao.impl.UserDaoImpl;
import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.ProductCategory;
import com.hzit.entity.User;
import com.hzit.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User queryUser(String name, String password) {
		return userDao.queryUser(name, password);
	}
	@Override
	public List<ProductCategory> queryFirstAll(int id) {
		
		return userDao.queryFirstAll(id);
	}
	@Override
	public List<Product> queryAll() {
		return userDao.queryAll();
	}
	@Override
	public void add(User user) {
		userDao.add(user);
	}
	@Override
	public List<News> queryAllNews() {
		return userDao.queryAllNews();
	}

}
