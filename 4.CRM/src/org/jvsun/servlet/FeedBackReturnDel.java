package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackReturnDAOFactory;

public class FeedBackReturnDel extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		BigDecimal feedId = new BigDecimal(request.getParameter("feedId"));
		boolean flag = FeedBackReturnDAOFactory.getDAOInstance().doDel(feedId);
		out.print(flag);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
