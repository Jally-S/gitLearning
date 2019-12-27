package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ModifyPasswordDAOFactory;

import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.VLoginPOJO;
import org.jvsun.pojo.WorkerPOJO;

public class ModifyPassword extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		VLoginPOJO pojologin = (VLoginPOJO)request.getSession().getAttribute("pojo");
		BigDecimal loginId = new BigDecimal(pojologin.getLoginId());
		String password = request.getParameter("password");
		boolean flag = ModifyPasswordDAOFactory.getDAOInstance().doUpdate(password, loginId);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();

	}

}
