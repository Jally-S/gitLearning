package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ProductDAOFactory;
import org.jvsun.pojo.ProductPOJO;

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 2583620542916967729L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pname = request.getParameter("pname");
		String pdescribe = request.getParameter("pdescribe");
		int pclass = Integer.parseInt(request.getParameter("pclass"));
		int pinventory = Integer.parseInt(request.getParameter("pinventory"));
		double price = Double.parseDouble(request.getParameter("price"));
		ProductPOJO pojo = new ProductPOJO(pname,pdescribe,pclass,pinventory,price);
		System.out.println("输出数据："+pojo.toString());
		boolean flag=ProductDAOFactory.getDAOInstance().doIns(pojo);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
