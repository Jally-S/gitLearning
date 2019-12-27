package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RepairReturnDAOFactory;
import org.jvsun.pojo.RepairReturnPOJO;

public class RepairReturnFindByReptId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal reptId = new BigDecimal(request.getParameter("reptId"));
		RepairReturnPOJO pojo = RepairReturnDAOFactory.getDAOInstance().findByReptId(reptId);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/repairReturnUpd.jsp").forward(request, response);
	}

}
