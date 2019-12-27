package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorProductDAOFactory;
import org.jvsun.pojo.CompetitorProductPOJO;

public class CompetitorProductQuery extends HttpServlet {

	private static final long serialVersionUID = 1676698970589685749L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pname = request.getParameter("pname");
		String cname = request.getParameter("cname");
		int clas = Integer.parseInt(request.getParameter("clas"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<CompetitorProductPOJO> list = CompetitorProductDAOFactory.getDAOInstance().findAllByPnameCnameClass(pname, cname, clas, pageSize, pageCurrent);
		int count = CompetitorProductDAOFactory.getDAOInstance().findAllCount();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>对手产品名称</th><th>对手名称</th><th>竞争级别</th><th>操作</th></tr>");
		for(CompetitorProductPOJO pojo : list){
			String pc="";
			if(pojo.getComProductClass()==1){
				pc="一级竞争";
			}
			else if(pojo.getComProductClass()==2){
				pc="二级竞争";
			}
			else if(pojo.getComProductClass()==3){
				pc="三级竞争";
			}
			else {
				pc="四级竞争";
			}
			sb.append("<tr>" +
					"<td>"+pojo.getComName()+"</td>" +
					"<td>"+pojo.getComProductName()+"</td>" +
					"<td>"+pc+"</td>" +
					"<td><a href='#' class='button border-main' onclick='goUpdate("+pojo.getComProductId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' class='button border-red' onclick='goDelete("+pojo.getComProductId()+")'>删除</a></td>" +
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
