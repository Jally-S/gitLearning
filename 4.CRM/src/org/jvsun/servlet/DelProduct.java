package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ProductDAOFactory;

public class DelProduct extends HttpServlet {
	private static final long serialVersionUID = 6186711344037370703L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int productId = Integer.parseInt(request.getParameter("productId"));
		boolean flag = ProductDAOFactory.getDAOInstance().doDel(productId);
		System.out.println(flag);
		out.print(flag);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}


}
