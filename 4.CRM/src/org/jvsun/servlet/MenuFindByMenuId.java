package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.MenuDAOFactory;
import org.jvsun.dao.factory.RoleDAOFactory;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;

public class MenuFindByMenuId extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		request.setCharacterEncoding("utf-8");

		BigDecimal MenuId = new BigDecimal(request.getParameter("MenuId"));
		MenuPOJO pojo = MenuDAOFactory.getDAOInstance().findByMenuId(MenuId);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/MenuUpdate.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
