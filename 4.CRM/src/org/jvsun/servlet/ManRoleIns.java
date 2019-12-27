package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ManRoleDAOFactory;
import org.jvsun.dao.factory.MenuDAOFactory;
import org.jvsun.pojo.MenuPOJO;

public class ManRoleIns extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		int loginId = Integer.parseInt(request.getParameter("loginId"));
	
		BigDecimal roleId = new BigDecimal(request.getParameter("roleId"));
		
	
	
		boolean flag = ManRoleDAOFactory.getDAOInstance().doIns(loginId,roleId);

		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}
}
