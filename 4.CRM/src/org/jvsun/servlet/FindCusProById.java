package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;
import org.jvsun.pojo.ProductPOJO;

public class FindCusProById extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		List<ProductPOJO> list = CustomerDAOFactory.getDAOInstance().findProductByCid(cid);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/customerProduct.jsp?cid="+cid).forward(request, response);
		 
		
	}
}
