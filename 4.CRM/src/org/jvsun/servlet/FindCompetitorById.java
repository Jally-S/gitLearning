package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorDAOFactory;
import org.jvsun.pojo.CompetitorPOJO;

public class FindCompetitorById extends HttpServlet {
	private static final long serialVersionUID = 2405516296512334594L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		System.out.println(cid);
		CompetitorPOJO pojo = CompetitorDAOFactory.getDAOInstance().findById(cid);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/updateCompetitor.jsp").forward(request, response);
		
	}
}
