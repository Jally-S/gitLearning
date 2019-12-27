package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorProductDAOFactory;

public class DelCompetitorProduct extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6739110642144133138L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		BigDecimal pid=new BigDecimal(request.getParameter("pid"));
		boolean flag = CompetitorProductDAOFactory.getDAOInstance().doDel(pid);
		System.out.println(flag);
		out.print(flag);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}


}