package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.dao.factory.FeedBackReturnDAOFactory;
import org.jvsun.dao.factory.RepairReturnDAOFactory;
import org.jvsun.pojo.FeedBackReturnPOJO;
import org.jvsun.pojo.VLoginPOJO;

public class FeedBackReturnUpd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		VLoginPOJO Vpojo = (VLoginPOJO) request.getSession().getAttribute("pojo");
		BigDecimal feedId = new BigDecimal(request.getParameter("feedId"));
		String feedName = request.getParameter("feedName");
		String feedContent = request.getParameter("feedContent");
		BigDecimal feedBackId =new BigDecimal(request.getParameter("feedBackId"));
		BigDecimal workerId = Vpojo.getUserId();
		Date time = new Date();
		FeedBackReturnPOJO pojo = new FeedBackReturnPOJO(feedId,feedName,feedContent,time,feedBackId,workerId,1);
		boolean flag = FeedBackReturnDAOFactory.getDAOInstance().doUpd(pojo);
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
