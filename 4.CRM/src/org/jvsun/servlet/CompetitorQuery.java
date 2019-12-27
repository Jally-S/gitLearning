package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorDAOFactory;
import org.jvsun.pojo.CompetitorPOJO;

public class CompetitorQuery extends HttpServlet {
	private static final long serialVersionUID = -5242140656876074302L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cname = request.getParameter("cname");
		int clas = Integer.parseInt(request.getParameter("clas"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<CompetitorPOJO> list = CompetitorDAOFactory.getDAOInstance().findAllByNameClass(cname,clas, pageSize, pageCurrent);
		int count = CompetitorDAOFactory.getDAOInstance().findAllCount();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>对手名称</th><th>对手级别</th><th>操作</th></tr>");
		for(CompetitorPOJO pojo : list){
			String pc="";
			if(pojo.getCompetitorClass()==1){
				pc="一级竞争对手";
			}
			else if(pojo.getCompetitorClass()==2){
				pc="二级竞争对手";
			}
			else if(pojo.getCompetitorClass()==3){
				pc="三级竞争对手";
			}
			else {
				pc="四级竞争对手";
			}
			sb.append("<tr>" +
					"<td>"+pojo.getCompetitorName()+"</td>" +
					"<td>"+pc+"</td>" +
					"<td><a href='#' class='button border-main' onclick='goUpdate("+pojo.getCompetitorId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' class='button border-red' onclick='goDelete("+pojo.getCompetitorId()+")'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' class='button border-main' onclick='goAddProduct("+pojo.getCompetitorId()+")'>新增对手产品</a></td>" +
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
