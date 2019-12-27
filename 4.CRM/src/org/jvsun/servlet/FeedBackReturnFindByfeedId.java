package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackReturnDAOFactory;
import org.jvsun.pojo.FeedBackReturnPOJO;

public class FeedBackReturnFindByfeedId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal feedId = new BigDecimal(request.getParameter("feedId"));
		FeedBackReturnPOJO pojo = FeedBackReturnDAOFactory.getDAOInstance().findByFeedId(feedId);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/feedBackReturnUpd.jsp").forward(request, response);
	}

}
