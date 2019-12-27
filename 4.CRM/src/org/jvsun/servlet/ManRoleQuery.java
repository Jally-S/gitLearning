package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ManRoleDAOFactory;
import org.jvsun.dao.factory.RoleDAOFactory;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.VLoginPOJO;

public class ManRoleQuery extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String userName = request.getParameter("userName");
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<VLoginPOJO> list = ManRoleDAOFactory.getDAOInstance().findAll(userName, pageSize, pageCurrent);
		List<RolePOJO> Rolelist = ManRoleDAOFactory.getDAOInstance().findAll();
		int count = ManRoleDAOFactory.getDAOInstance().findCountByNameState(userName);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();// worker_id, worker_name,
												// worker_sex, "
		// + "worker_tel, worker_adress, w_acount, is_delete
		sb.append("<input type='hidden' id='count' value='" + count + "'/>");
		sb
				.append("<table id='mytable' class='table table-hover text-center'><tr><th>姓名</th><th>类别</th><th>角色</th></tr>");
		for (VLoginPOJO pojo : list) {

			String state = "";
			if (pojo.getUserTape() == 1) {
				state = "职工";
			} else {

				state = "客户";
			}
			List<String> UserRoleList = ManRoleDAOFactory.getDAOInstance().findUserRole(pojo.getUserId());

			sb.append("<tr>" +"<td>" + pojo.getUserName()+ "</td>" +"<td>" + state  + "</td>"+"<td>");
			for (RolePOJO Rolepojo : Rolelist) {
				
				if(UserRoleList.indexOf(Rolepojo.getRoleName())==-1){
					sb.append("<input type='checkbox' onchange='goIns("+Rolepojo.getRoleId()+","+pojo.getLoginId()+")'/>"+Rolepojo.getRoleName());
				}else{
					sb.append("<input type='checkbox' checked='checked' onchange='goDel("+Rolepojo.getRoleId()+","+pojo.getLoginId()+")'/>"+Rolepojo.getRoleName());
				}
			}
			
			sb.append("</td></tr>");
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
