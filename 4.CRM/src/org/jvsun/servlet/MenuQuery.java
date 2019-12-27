package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.MenuDAOFactory;
import org.jvsun.dao.factory.RoleDAOFactory;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;

public class MenuQuery extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String MenuName = request.getParameter("MenuName");
		int isDelete = Integer.parseInt(request.getParameter("isDelete"));

		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<MenuPOJO> list = MenuDAOFactory.getDAOInstance().findAll(MenuName,
				isDelete, pageSize, pageCurrent);

		int count = MenuDAOFactory.getDAOInstance().findCountByNameState(
				MenuName, isDelete);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();// worker_id, worker_name,
		// worker_sex, "
		// + "worker_tel, worker_adress, w_acount, is_delete
		sb.append("<input type='hidden' id='count' value='" + count + "'/>");
		sb
				.append("<table id='mytable' class='table table-hover text-center'><tr><th>菜单名字</th><th>菜单路径</th><th>使用状态</th><th>操作</th></tr>");
		for (MenuPOJO pojo : list) {

			String state = "";
			if (pojo.getIsDelete() == 1) {
				state = "可用";
			} else {

				state = "停用";
			}

			sb.append("<tr>"+"<td>"
					+ pojo.getMenuName() + "</td>" +

					"<td>" + pojo.getMenuPath() + "</td>" + "<td>" + state
					+ "</td>" +

					"<td><a class='button border-main' href='#' onclick='goUpdate(" + pojo.getMenuId()
					+ ")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>" + "</tr>");
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
