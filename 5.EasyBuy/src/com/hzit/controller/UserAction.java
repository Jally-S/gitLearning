package com.hzit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.ProductCategory;
import com.hzit.entity.User;
import com.hzit.service.UserService;
import com.hzit.service.impl.UserServiceImpl;

/**
 * 用户登陆
 * 
 * @author ZhaoQu
 * 
 */
public class UserAction {
	private UserService userService = new UserServiceImpl();
	private User user;
	private List<ProductCategory> list;
	private List<ProductCategory> list2;
	private List<ProductCategory> list3;
	private List<Product> list4;
	private List<News> list5;
	//购物车跳转到登录界面的标识   为了确保可以跳回到购物车的结算界面
	private int flag;
	
	
	

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List<News> getList5() {
		return list5;
	}

	public void setList5(List<News> list5) {
		this.list5 = list5;
	}

	public List<Product> getList4() {
		return list4;
	}

	public void setList4(List<Product> list4) {
		this.list4 = list4;
	}

	public List<ProductCategory> getList2() {
		return list2;
	}

	public void setList2(List<ProductCategory> list2) {
		this.list2 = list2;
	}

	public List<ProductCategory> getList3() {
		return list3;
	}

	public void setList3(List<ProductCategory> list3) {
		this.list3 = list3;
	}

	public List<ProductCategory> getList() {
		return list;
	}

	public void setList(List<ProductCategory> list) {
		this.list = list;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//登陆
	public String login() {
		// System.out.println(user.getLoginName());
		// System.out.println(user.getPassword());
		user = userService.queryUser(user.getLoginName(), user.getPassword());
		// 获取原生web资源
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 看数据库查出的内容是否为空 以此来判断登陆是否成功
		if (user != null) {
			session.setAttribute("user", user);
			// System.out.println("登陆成功");
		} else {
			return "login_fail";
		}
		String result = "login_ok";
		
		//购物车确认结算时判断跳转到哪个页面
		if(flag == 1){
			result = "login_cart";
		}
		
		return result;
	}
	
	//注册
	public String regist(){
		userService.add(user);
		//注册成功之后跳转到登陆页面
		return "login_fail";
	}
	
	//注销
	public String logout(){
		HttpSession session = ServletActionContext.getRequest().getSession();
//		//清除特定的session
//		session.removeAttribute("user");
		//清除所有的session   及退出登陆之后  之前的购物车将不存在
		session.invalidate();
		return "login_ok";
	}
	
	// 查询main.jsp展示的数据
	public String queryAll() {
		list = userService.queryFirstAll(1);
		list2 = userService.queryFirstAll(2);
		list3 = userService.queryFirstAll(3);
		list4 = userService.queryAll();
		list5 = userService.queryAllNews();
		//因为其他页面要用这些数据   因此将其存入session中
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("list", list);
		session.setAttribute("list2", list2);
		session.setAttribute("list3", list3);
		session.setAttribute("list4", list4);
		session.setAttribute("list5", list5);
		return "query_ok";
	}

}
