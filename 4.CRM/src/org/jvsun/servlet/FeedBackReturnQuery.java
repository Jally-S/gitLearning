package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.dao.factory.FeedBackReturnDAOFactory;
import org.jvsun.pojo.FeedBackPOJO;
import org.jvsun.pojo.FeedBackReturnPOJO;
import org.jvsun.pojo.VLoginPOJO;
public class FeedBackReturnQuery extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		VLoginPOJO Vpojo = (VLoginPOJO) request.getSession().getAttribute("pojo");
		String workerName = Vpojo.getUserName();
		BigDecimal workerId = Vpojo.getUserId();
		String feedName = request.getParameter("feedName");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<FeedBackReturnPOJO> list = FeedBackReturnDAOFactory.getDAOInstance().findByFeedName(feedName, pageSize, pageCurrent);
		int count  = FeedBackReturnDAOFactory.getDAOInstance().findCountByFeedName(feedName);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>回执单名称</th><th>回执单描述</th><th>整改时间</th><th>产品名称</th><th>整改员</th><th>操作</th></tr>");
		for(FeedBackReturnPOJO pojo : list ){
			String time = new SimpleDateFormat("yyyy-MM-dd").format(pojo.getFeedTime());
			FeedBackPOJO fpojo = FeedBackDAOFactory.getDAOInstance().findByFeedBackId(pojo.getFeedBackId());
		String s = "";
		if(pojo.getFeedContent() !=null){
			s = pojo.getFeedContent();
		}
		sb.append("<tr>" +
					"<td>"+pojo.getFeedName()+"</td>" +
					"<td>"+s+"</td>" +
					"<td>"+time+"</td>" +
					"<td>"+fpojo.getProductName()+"</td>" +
					"<td>"+workerName+"</td>" +
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getFeedId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' href='#' onclick='goDelete("+pojo.getFeedId()+")'>删除</a></td>" +
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
