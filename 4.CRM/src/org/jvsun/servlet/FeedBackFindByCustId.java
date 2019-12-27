package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.pojo.FeedBackPOJO;
/**
 * 通过反馈ID查询反馈单
 * @author Administrator
 *
 */
public class FeedBackFindByCustId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal feedBackId= new BigDecimal(request.getParameter("feedBackId"));//反馈id
		FeedBackPOJO fpojo = FeedBackDAOFactory.getDAOInstance().findOneByCustomerId(feedBackId);
		request.setAttribute("fpojo", fpojo);//设置request属性
		request.getRequestDispatcher("/manager/UpdFeedBack.jsp").forward(request, response);
	}

}
