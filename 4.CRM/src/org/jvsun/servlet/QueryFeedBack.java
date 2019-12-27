package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.pojo.FeedBackPOJO;
/**
 * 查询所有反馈
 * @author Administrator
 *
 */
public class QueryFeedBack extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String productName = request.getParameter("productName");//产品名
		BigDecimal customerId =new BigDecimal(request.getParameter("customerId"));//客户id
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		//调用分页查询方法
		List<FeedBackPOJO> list = FeedBackDAOFactory.getDAOInstance().findById(productName,customerId, pageSize, pageCurrent);
		//调用查询所有反馈单的笔数的方法
		int count = FeedBackDAOFactory.getDAOInstance().findById(customerId);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		//输出的表单
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>产品名称</th><th>反馈信息</th><th>处理结果</th><th>操作</th></tr>");
		for(FeedBackPOJO pojo : list){
			String feedContent;
			if(null==pojo.getFeedContent()){
				feedContent="暂未处理";
			}else{
				feedContent=pojo.getFeedContent();
			}
			sb.append("<tr>" +
					"<td>"+pojo.getProductName()+"</td>" +
					"<td>"+pojo.getFeedbackContent()+"</td>" +
					"<td>"+feedContent+"</td>" +
					"<td><a href='#' class='button border-main' onclick='goUpdate("+pojo.getFeedbackId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
							"<a class='button border-red' href='#' onclick='goDelete("+pojo.getFeedbackId()+")'>删除</a></td>" +
					"</tr>");
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
