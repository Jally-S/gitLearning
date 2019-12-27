package org.jvsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ProductDAOFactory;
import org.jvsun.pojo.ProductPOJO;

public class FindProductById extends HttpServlet {

	private static final long serialVersionUID = -2389644711884756944L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int pid = Integer.parseInt(request.getParameter("pid"));
		System.out.println(pid);
		ProductPOJO pojo = ProductDAOFactory.getDAOInstance().findById(pid);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/updateProduct.jsp").forward(request, response);
		
	}
}
