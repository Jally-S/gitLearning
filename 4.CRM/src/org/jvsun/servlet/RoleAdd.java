package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RoleDAOFactory;
import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.WorkerPOJO;

public class RoleAdd extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
	
		String roleName = request.getParameter("roleName");
		int  roleMark = Integer.parseInt(request.getParameter("roleMark"));
	
		int isdelete =Integer.parseInt( request.getParameter("isdelete"));
	
		RolePOJO pojo = new RolePOJO(roleName,roleMark,isdelete);
		boolean flag = RoleDAOFactory.getDAOInstance().doIns(pojo);
		
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}
}
