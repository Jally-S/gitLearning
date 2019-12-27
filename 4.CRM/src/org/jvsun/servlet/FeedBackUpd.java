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
 * 反馈信息修改
 * @author Administrator
 *
 */
public class FeedBackUpd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal feedBackId = new BigDecimal(request.getParameter("feedBackId"));//反馈id
		String FeedbackContent = request.getParameter("feedBackContent");//反馈信息
		boolean flag = FeedBackDAOFactory.getDAOInstance().update(feedBackId, FeedbackContent);//调用反馈更新方法
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

}
