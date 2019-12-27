package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ProductDAOFactory;
import org.jvsun.pojo.ProductPOJO;

public class ProductUpd extends HttpServlet {

	private static final long serialVersionUID = 5836876013350264597L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int pid = Integer.parseInt(request.getParameter("pid"));
		System.out.println("********"+pid);
		String pname = request.getParameter("pname");
		String pdesc = request.getParameter("pdesc");
		int pclass = Integer.parseInt(request.getParameter("pclass"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		double price = new Double(request.getParameter("price"));
		ProductPOJO pojo = new ProductPOJO(pid, pname,pdesc,pclass,pin,price);
		boolean flag = ProductDAOFactory.getDAOInstance().doUpd(pojo);
		System.out.println("输出数据："+pojo.toString());
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
}