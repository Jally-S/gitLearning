package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorDAOFactory;
import org.jvsun.pojo.CompetitorPOJO;

public class CompetitorUpd extends HttpServlet {
	private static final long serialVersionUID = -3822947388555320577L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		int clas = Integer.parseInt(request.getParameter("clas"));
		CompetitorPOJO pojo = new CompetitorPOJO(cid, cname,clas);
		boolean flag = CompetitorDAOFactory.getDAOInstance().doUpd(pojo);
		System.out.println("输出数据："+pojo.toString());
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