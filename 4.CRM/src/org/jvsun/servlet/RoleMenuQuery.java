package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ManRoleDAOFactory;
import org.jvsun.dao.factory.RoleMenuDAOFactory;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.VLoginPOJO;

public class RoleMenuQuery extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String roleName = request.getParameter("roleName");
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<RolePOJO> list = RoleMenuDAOFactory.getDAOInstance().findAll(
				roleName, pageSize, pageCurrent);//所有角色
		List<MenuPOJO> menuList = RoleMenuDAOFactory.getDAOInstance().findAll();//所有菜单
		
		int count = RoleMenuDAOFactory.getDAOInstance().findCountByNameState(
				roleName);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();// worker_id, worker_name,
		// worker_sex, "
		// + "worker_tel, worker_adress, w_acount, is_delete
		sb.append("<input type='hidden' id='count' value='" + count + "'/>");
		sb
				.append("<table id='mytable' class='table table-hover text-center'><tr><th>角色</th><th>菜单</th></tr>");
		for (RolePOJO pojo : list) {

			
			List<String> roleMenuList = RoleMenuDAOFactory.getDAOInstance().findRoleMenu(pojo.getRoleId());

			sb.append("<tr>" + "<td>" + pojo.getRoleName() + "</td>"  + "<td>");
			for (MenuPOJO MenuPOJO : menuList) {

				if (roleMenuList.indexOf(MenuPOJO.getMenuName()) == -1) {
					sb.append("<input type='checkbox' onchange='goIns("
							+ pojo.getRoleId() + "," + MenuPOJO.getMenuId()
							+ ")'/>" + MenuPOJO.getMenuName());
				} else {
					sb.append("<input type='checkbox' checked='checked' onchange='goDel("
									+ pojo.getRoleId()
									+ ","
									+MenuPOJO.getMenuId()
									+ ")'/>"
									+ MenuPOJO.getMenuName());
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
