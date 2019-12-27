package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RoleDAOFactory;
import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.WorkerPOJO;

public class RoleFindByRoleIDId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
String path = request.getContextPath();
request.setCharacterEncoding("utf-8");

BigDecimal RoleId = new BigDecimal(request.getParameter("RoleId"));
RolePOJO pojo = RoleDAOFactory.getDAOInstance().findByRoleId(RoleId);
request.setAttribute("pojo", pojo);
request.getRequestDispatcher("/manager/RoleUpdate.jsp").forward(request, response);

}


public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
this.doGet(request, response);

}
}
