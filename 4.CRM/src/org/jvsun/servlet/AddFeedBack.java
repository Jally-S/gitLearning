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
import org.jvsun.pojo.FeedBackPOJO;
import org.jvsun.pojo.VLoginPOJO;
/**
 * 添加反馈信息
 * @author Administrator
 *
 */
public class AddFeedBack extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");//设置相应的MIME类型
		VLoginPOJO Vpojo = (VLoginPOJO) request.getSession().getAttribute("pojo");//获取登录的对象
		BigDecimal customerId = Vpojo.getUserId();//取得登录的客户ID
		String productName = request.getParameter("productName");//产品名
		String feedBack = request.getParameter("feedBack");//反馈信息
		//BigDecimal feedbackId, BigDecimal customerId,
		//Date feedbackDate, String productName, String feedbackContent,
		//String feedContent, int isOver, int isDelete
		Date time =new Date();
		FeedBackPOJO pojo = new FeedBackPOJO(customerId,time,productName,feedBack,"",0,1);//创建反馈对象
		boolean flag = FeedBackDAOFactory.getDAOInstance().doIns(pojo);//调用添加反馈的方法
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

}
