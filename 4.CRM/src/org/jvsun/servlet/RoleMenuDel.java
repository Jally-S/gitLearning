package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ManRoleDAOFactory;
import org.jvsun.dao.factory.RoleMenuDAOFactory;

public class RoleMenuDel extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		
		BigDecimal menuId = new BigDecimal(request.getParameter("menuId"));
		BigDecimal roleId = new BigDecimal(request.getParameter("roleId"));

		boolean flag = RoleMenuDAOFactory.getDAOInstance()
				.doDel(roleId, menuId);

		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}

}
