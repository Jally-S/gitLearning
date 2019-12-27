package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorProductDAOFactory;
import org.jvsun.pojo.CompetitorProductPOJO;

public class FindCompetitorProductById extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1756451500005771188L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		System.out.println(cid);
		CompetitorProductPOJO pojo = CompetitorProductDAOFactory.getDAOInstance().findById(cid);
		request.setAttribute("pojo", pojo);
		System.out.println(pojo.getComName()+"对手名");
		System.out.println(pojo.getComProductName()+"对手产品");
		request.getRequestDispatcher("/manager/updateCompetitorProduct.jsp").forward(request, response);
		
	}
}
