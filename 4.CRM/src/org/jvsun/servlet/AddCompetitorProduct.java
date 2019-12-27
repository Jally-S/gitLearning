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

public class AddCompetitorProduct extends HttpServlet {
	private static final long serialVersionUID = -7146564650433966244L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("pid")+"+++++");
		BigDecimal pid=new BigDecimal(request.getParameter("pid"));
		String cname = request.getParameter("cname");
		int clas = Integer.parseInt(request.getParameter("clas"));
		CompetitorProductPOJO pojo = new CompetitorProductPOJO(pid,cname,clas,1);//pojo构造和更新重复了  无奈加个1
		boolean flag=CompetitorProductDAOFactory.getDAOInstance().doIns(pojo);
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
