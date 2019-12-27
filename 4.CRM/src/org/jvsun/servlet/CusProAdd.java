package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;

public class CusProAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal pid = new BigDecimal(request.getParameter("pid"));
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		boolean flag=CustomerDAOFactory.getDAOInstance().doInsCP(pid, cid);
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
