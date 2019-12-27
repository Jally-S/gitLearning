package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;
import org.jvsun.pojo.CustomerPOJO;

/**
 *添加客户
 *
 */
public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 3710905267897279403L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cname = request.getParameter("cname");
		int csex = Integer.parseInt(request.getParameter("csex"));
		String ctel = request.getParameter("ctel");
		String cadress = request.getParameter("cadress");
		int cage = Integer.parseInt(request.getParameter("cage"));
		String acount = request.getParameter("acount");
		String company = request.getParameter("company");
		CustomerPOJO pojo = new CustomerPOJO(cname,csex,ctel,cadress,cage,acount,company);
		System.out.println("输出数据："+pojo.toString());
		boolean flag=CustomerDAOFactory.getDAOInstance().doIns(pojo);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
