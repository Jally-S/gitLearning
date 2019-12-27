package com.hzit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;

import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.service.GoodsService;
import com.hzit.service.impl.GoodsServiceImpl;

/**
 * 商品Action
 * 
 * @author ZhaoQu
 * 
 */
public class GoodsAction {
	private List<Product> list6;
	private List<News> list7;
	// 显示每页展示的商品条数
	private int countPrePage = 4;
	// 显示每页展示的新闻条数
	private int countPrePage2 = 8;
	// 获取初始化页面参数
	private int page;
	// 将分页的总页数返回给客户端
	private int pages;
	// 返回分页的总条数
	private int counts;
	private GoodsService goodsService = new GoodsServiceImpl();
	private int level;// 获取main.jsp的参数
	private int id;// 获取此时商品的id参数
	private Product product;
	private String keyWord;
	private String method;//区分分页的时候进的是哪个方法
	
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCountPrePage2() {
		return countPrePage2;
	}

	public void setCountPrePage2(int countPrePage2) {
		this.countPrePage2 = countPrePage2;
	}

	public List<News> getList7() {
		return list7;
	}

	public void setList7(List<News> list7) {
		this.list7 = list7;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Product> getList6() {
		return list6;
	}

	public void setList6(List<Product> list6) {
		this.list6 = list6;
	}

	// goodsList页面实现分页查询
	public String queryGoodsBylevel() {

		// 得到商品分页前的总数
		counts = goodsService.getCounts(level, id);
		// 分页的总页数
		pages = goodsService.getPages(countPrePage, level, id);

		// 防止外部非法输入页面数 以及屏蔽上一页一直减小为负数 下一页一直增大超过了总的页数
		if (page <= 0) {
			page = 1;
		}
		if (page > pages) {
			page = pages;
		}
		// 分页之后每页查到的数据
		list6 = goodsService.getGoodsLevel(page, countPrePage, level, id);

		// 将此时的level和id带过去
		level = level;
		id = id;
		page = page;
		method = "queryList";
		return "query_ok";
	}

	// 分页查询所有的新闻资讯
	public String getAllNews() {
		// 得到分页的总页数
		pages = goodsService.getNewsPages(countPrePage2);
		// 防止外部非法输入页面数 以及屏蔽上一页一直减小为负数 下一页一直增大超过了总的页数
		if (page <= 0) {
			page = 1;
		}
		if (page > pages) {
			page = pages;
		}
		// 通过分页查询所有的新闻
		list7 = goodsService.getAllNews(page, countPrePage2);
		return "newsList_ok";
	}
	
	//通过id查询对应的商品
	public String  getProductById(){
		product = goodsService.getProductById(id);
		return "product_ok";
	}
	
	//通过名称模糊查询商品
	public String queryByName(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String pagestr = request.getParameter("page");
//		int page2 = Integer.parseInt(pagestr);
		
		// 得到分页的总页数
		pages = goodsService.getProductPages(keyWord,countPrePage);
		//System.out.println(keyWord);
		// 防止外部非法输入页面数 以及屏蔽上一页一直减小为负数 下一页一直增大超过了总的页数
		if (page <= 0) {
			page = 1;
		}
		//此处外层的if是判断当模糊查询没查询到值的话说明没有此商品
		//确保有商品的情况下仔进行查询
		if(pages!=0){
			if (page > pages) {
				page = pages;
			}
		}
		
		// 得到商品分页前的总数
		counts = goodsService.getProductCounts(keyWord);
		list6 = goodsService.queryByName(page, countPrePage,keyWord);
		method = "search";
		return "query_ok";
	}
}
