package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ContractDAOFactory;
import org.jvsun.pojo.ContractPOJO;

public class FindContractById extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		ContractPOJO pojo = ContractDAOFactory.getDAOInstance().findById(cid);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/updateContract.jsp").forward(request, response);
		
	}
}
