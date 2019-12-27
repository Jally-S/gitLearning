package com.hzit.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.hzit.dao.GoodsDao;
import com.hzit.dao.impl.GoodsDaoImpl;
import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.User;
import com.hzit.entity.UserAddress;
import com.hzit.service.GoodsService;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDao goodsDao = new GoodsDaoImpl();

	@Override
	public List<Product> getGoodsLevel(int page, int countPrePage, int type,
			int id) {
		// 计算出开始页面和结束页面
		int start = (page - 1) * countPrePage;
		int end = countPrePage;
		return goodsDao.getGoodsLevel(start, end, type, id);
	}

	// 返回此次查询应该展示的页数
	@Override
	public int getPages(int countPrePage, int type, int id) {
		// 查询所有的页数
		int counts = goodsDao.getCount(type, id);
		// 动态计算应该返回的页数
		int pages = 0;
		if (counts % countPrePage == 0) {
			pages = counts / countPrePage;
		} else {
			pages = counts / countPrePage + 1;
		}
		return pages;
	}

	@Override
	public int getCounts(int type, int id) {
		int counts = goodsDao.getCount(type, id);
		return counts;
	}

	@Override
	public List<News> getAllNews(int page, int countPrePage2) {
		// 计算出开始页面和结束页面
		int start = (page - 1) * countPrePage2;
		int end = countPrePage2;
		return goodsDao.getAllNews(start,end);
	}

	@Override
	public int getNewsPages(int countPrePage2) {
		// 查询所有的页数
		int counts = goodsDao.getNewsCount();
		// 动态计算应该返回的页数
		int pages = 0;
		if (counts % countPrePage2 == 0) {
			pages = counts / countPrePage2;
		} else {
			pages = counts / countPrePage2 + 1;
		}
		return pages;
	}

	@Override
	public Product getProductById(int id) {
		
		return goodsDao.getProductById(id);
	}

	@Override
	public List<Product> queryByName(int page,int countPrePage,String keyWord) {
		// 计算出开始页面和结束页面
		int start =(page-1)*countPrePage;
		int end = countPrePage;
		return goodsDao.queryByName(start,end,keyWord);
	}

	@Override
	public int getProductPages(String keyWord, int countPrePage) {
		// 查询所有的页数
		int counts = goodsDao.getProdctCount(keyWord);
		// 动态计算应该返回的页数
		int pages = 0;
		if (counts % countPrePage == 0) {
			pages = counts / countPrePage;
		} else {
			pages = counts / countPrePage + 1;
		}
		return pages;
	}

	@Override
	public int getProductCounts(String keyWord) {
		
		return goodsDao.getProdctCount(keyWord);
	}

	@Override
	public List<UserAddress> getAddressById(int entity) {
		
		return goodsDao.getAddressById(entity);
	}

	@Override
	public void addAdress(String newAddress, String newRemark) {
		//获取当前的用户id  和创建时间
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		//获取当前系统时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = sdf.format(date);
		goodsDao.addAdress(newAddress, newRemark, data, user);
	}

	@Override
	public void addOrders(int counts, User user, String seriaNumber, String data,String userAddress) {
		goodsDao.addOrders(counts, user, seriaNumber, data, userAddress);
		
	}

	@Override
	public UserAddress getAddressByAddressId(int addressId) {
		return goodsDao.getAddressByAddressId(addressId);
	}

//	 public static void main(String[] args) {
//	 GoodsService goodsService = new GoodsServiceImpl();
//	 goodsService.addAdress("wfde","waqf");
//	 }
}
