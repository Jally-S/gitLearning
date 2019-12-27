package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CompetitorDAOFactory;
import org.jvsun.pojo.CompetitorPOJO;

public class AddCompetitor extends HttpServlet {
	private static final long serialVersionUID = -5311277726043202659L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cname = request.getParameter("cname");
		int clas = Integer.parseInt(request.getParameter("clas"));
		CompetitorPOJO pojo = new CompetitorPOJO(cname,clas);
		System.out.println("输出数据："+pojo.toString());
		boolean flag=CompetitorDAOFactory.getDAOInstance().doIns(pojo);
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
