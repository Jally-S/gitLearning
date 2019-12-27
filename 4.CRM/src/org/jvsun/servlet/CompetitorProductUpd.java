package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorProductDAOFactory;
import org.jvsun.pojo.CompetitorProductPOJO;

public class CompetitorProductUpd extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4491009013586331136L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		String pname = request.getParameter("pname");
		int clas = Integer.parseInt(request.getParameter("clas"));
		CompetitorProductPOJO pojo = new CompetitorProductPOJO(cid,pname,clas);
		boolean flag = CompetitorProductDAOFactory.getDAOInstance().doUpd(pojo);
		System.out.println("输出数据sss："+pojo.toString());
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