package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackDAOFactory;
/**
 * 反馈单删除
 * @author Administrator
 *
 */
public class FeedBackDel extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		BigDecimal feedBackId = new BigDecimal(request.getParameter("feedBackId"));//反馈单ID
		boolean flag = FeedBackDAOFactory.getDAOInstance().doDel(feedBackId);//调用反馈单删除的方法
		out.print(flag);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}

}
