package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;
import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.pojo.FeedBackPOJO;

public class FeedBackQuery extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String productName = request.getParameter("productName");
		int isOver =Integer.parseInt(request.getParameter("isOver")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<FeedBackPOJO> list = FeedBackDAOFactory.getDAOInstance().findByProductName(productName, isOver, pageSize, pageCurrent);
		int count  = FeedBackDAOFactory.getDAOInstance().findCountByProductName(productName, isOver);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center' ><tr><th>产品名称</th><th>反馈描述</th><th>反馈时间</th><th>客户</th><th>是否解决</th><th>操作</th></tr>");
		for(FeedBackPOJO pojo : list){
			CustomerPOJO cpojo = CustomerDAOFactory.getDAOInstance().findById(pojo.getCustomerId());
			String time = new SimpleDateFormat("yyyy-MM-dd").format(pojo.getFeedbackDate());
			String isOver1 = "";
			if(pojo.getIsOver() ==0){
				isOver1 = "未解决";
			}else{
				isOver1 = "已解决";
			}
		sb.append("<tr>" +
					"<td>"+pojo.getProductName()+"</td>" +
					"<td>"+pojo.getFeedbackContent()+"</td>" +
					"<td>"+time+"</td>" +
					"<td>"+cpojo.getCustomerName()+"</td>" +
					"<td>"+isOver1+"</td>" );
		if(pojo.getIsOver() == 0){
			sb.append("<td><a class='button border-main' href='#'onclick = 'goAdd("+pojo.getFeedbackId()+")'>整改</a>&nbsp</td>");
			System.out.println(pojo.getIsOver());
		}else{
			sb.append("<td>&nbsp</td>");
		}
		}
		sb.append("</table>");
		out.print(sb.toString());
		out.close();
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
