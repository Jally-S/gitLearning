package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RepairDAOFactory;
import org.jvsun.dao.factory.RepairReturnDAOFactory;
import org.jvsun.pojo.RepairPOJO;
import org.jvsun.pojo.RepairReturnPOJO;

public class RepairReturnQuery extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String reptName = request.getParameter("reptName");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<RepairReturnPOJO> list = RepairReturnDAOFactory.getDAOInstance().findByReptName(reptName, pageSize, pageCurrent);
		int count  = RepairReturnDAOFactory.getDAOInstance().findCountByReptName(reptName);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>回执单名称</th><th>回执单描述</th><th>维修时间</th><th>产品名称</th>" +
				"<th>操作</th></tr>");
		for(RepairReturnPOJO pojo : list ){
			RepairPOJO rpojo = RepairDAOFactory.getDAOInstance().findByRepairIdJi(pojo.getRepairId());
			System.out.println(pojo.getRepairId());
		sb.append("<tr>" +
					"<td>"+pojo.getReptName()+"</td>" +
					"<td>"+pojo.getReptContent()+"</td>" +
					"<td>"+pojo.getReptTime()+"</td>" +
					"<td>"+rpojo.getProductName()+"</td>" +
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getReptId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' href='#' onclick='goDelete("+pojo.getReptId()+")'>删除</a></td>" +
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
