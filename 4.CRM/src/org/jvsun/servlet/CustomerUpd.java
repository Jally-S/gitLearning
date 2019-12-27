package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;
import org.jvsun.pojo.CustomerPOJO;

public class CustomerUpd extends HttpServlet {
	private static final long serialVersionUID = 4651212200417322831L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal customerId = new BigDecimal(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		int csex = Integer.parseInt(request.getParameter("csex"));
		String ctel = request.getParameter("ctel");
		String cadress = request.getParameter("cadress");
		int cage = Integer.parseInt(request.getParameter("cage"));
		String acount = request.getParameter("acount");
		String company = request.getParameter("company");
		int isDelete=Integer.parseInt(request.getParameter("isDelete"));
		int roleMark=Integer.parseInt(request.getParameter("roleMark"));
		CustomerPOJO pojo = new CustomerPOJO(customerId, cname,csex,ctel,cadress,cage,acount, company,isDelete, roleMark);
		System.out.println("输出数据："+pojo.toString());
		boolean flag = CustomerDAOFactory.getDAOInstance().doUpd(pojo);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
