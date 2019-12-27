package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.NeedDAOFactory;
import org.jvsun.pojo.NeedPOJO;

public class AddNeed extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9150979335543513306L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal customerId = new BigDecimal(request.getParameter("customerId"));
		String hobby=request.getParameter("hobby");
		String productName=request.getParameter("productName");
		NeedPOJO pojo = new NeedPOJO(customerId,hobby,productName);
		System.out.println("输出数据："+pojo.toString());
		boolean flag=NeedDAOFactory.getDAOInstance().doIns(pojo);
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
