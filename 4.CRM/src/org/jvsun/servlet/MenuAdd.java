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

public class MenuAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		BigDecimal menuid = new BigDecimal(request.getParameter("menuid"));
		String menuname = request.getParameter("menuname");
		String menupath = request.getParameter("menupath");

		int isdelete = Integer.parseInt(request.getParameter("isdelete"));
		MenuPOJO pojo = new MenuPOJO(menuid, menuname, menupath, isdelete);
		boolean flag = MenuDAOFactory.getDAOInstance().doIns(pojo);

		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}
}
