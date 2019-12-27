package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.WorkerPOJO;


public class WorkerFindByWorkerId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		request.setCharacterEncoding("utf-8");
	
		BigDecimal workerId = new BigDecimal(request.getParameter("workerId"));
		WorkerPOJO pojo = WorkerDAOFactory.getDAOInstance().findByWorkerId(workerId);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/WorkerUpdate.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
		
	}

}
