package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;
import org.jvsun.pojo.CustomerPOJO;

/**
 * 根据id查询客户，用于客户信息的更改
 *
 */
public class FindCustomerById extends HttpServlet {

	private static final long serialVersionUID = -945808223222062489L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal cID = new BigDecimal(request.getParameter("cid"));
		CustomerPOJO pojo = CustomerDAOFactory.getDAOInstance().findById(cID);
		request.setAttribute("pojo", pojo);
		request.getRequestDispatcher("/manager/updateCustomer.jsp").forward(request, response);
		
	}
}
