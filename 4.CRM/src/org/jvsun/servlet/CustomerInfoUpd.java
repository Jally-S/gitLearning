package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerInfoDAOFactory;
import org.jvsun.pojo.CustomerPOJO;
/**
 * 客户个人信息更新
 * @author Administrator
 *
 */
public class CustomerInfoUpd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//参数customerId customerName customerSex customerAge customerTel customerAdress company
		BigDecimal customerId = new BigDecimal(request.getParameter("customerId"));//客户id
		String customerName = request.getParameter("customerName");//客户名
		int customerSex = Integer.parseInt(request.getParameter("customerSex"));//客户性别
		int customerAge = Integer.parseInt(request.getParameter("customerAge"));//客户年龄
		String customerTel = request.getParameter("customerTel");//客户电话
		String customerAdress = request.getParameter("customerAdress");//客户地址
		String company = request.getParameter("company");//客户公司
		CustomerPOJO pojo = new CustomerPOJO(customerId,customerName,customerSex,customerTel,customerAdress,customerAge,company);
		boolean flag = CustomerInfoDAOFactory.getDAOInstance().doUpd(pojo);//调用客户信息更新的方法
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

}
