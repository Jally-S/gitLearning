package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RoleDAOFactory;
import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.WorkerPOJO;

public class RoleQuery extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String RoleName = request.getParameter("RoleName");
		int isDelete = Integer.parseInt(request.getParameter("isDelete"));
	
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<RolePOJO> list = RoleDAOFactory.getDAOInstance().findAll(RoleName, isDelete,
				 pageSize,pageCurrent);
		
		int count = RoleDAOFactory.getDAOInstance().findCountByNameState(RoleName, isDelete);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();//worker_id, worker_name, worker_sex, "
		//+ "worker_tel, worker_adress, w_acount, is_delete 
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>角色名字</th><th>角色标识</th><th>使用状态</th><th>操作</th></tr>");
		for(RolePOJO pojo : list){
		
			String state= "";
			if(pojo.getIsDelete() == 1){
				state = "可用";
			}
			else{
				
				state = "停用";
			}
			
		
			sb.append("<tr>" +
					
					"<td>"+pojo.getRoleName()+"</td>" +
				
					"<td>"+pojo.getRoleMark()+"</td>" +
					"<td>"+state+"</td>" +
			
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getRoleId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
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
