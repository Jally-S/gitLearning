package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.NeedDAOFactory;
import org.jvsun.pojo.NeedPOJO;

public class NeedQuery extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1399789439240094714L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cname = request.getParameter("cname");
		String hobby = request.getParameter("hobby");
		String pname = request.getParameter("pname");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<NeedPOJO> list = NeedDAOFactory.getDAOInstance().findAllByCusHobbyProduct(cname, hobby, pname, pageSize, pageCurrent);
		int count = NeedDAOFactory.getDAOInstance().findAllCount();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>客户名称 </th><th>客户爱好</th><th>产品名称</th><th>操作</th></tr>");
		for(NeedPOJO pojo : list){
			sb.append("<tr>" +
					"<td>"+pojo.getCustomerName()+"</td>" +
					"<td>"+pojo.getNhobby()+"</td>" +
					"<td>"+pojo.getProductName()+"</td>" +
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getNeedId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' href='#' onclick='goDelete("+pojo.getNeedId()+")'>删除</a></td>" +
					"</tr>");
		}
		sb.append("</table>");
		out.print(sb.toString());
		out.close();
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
