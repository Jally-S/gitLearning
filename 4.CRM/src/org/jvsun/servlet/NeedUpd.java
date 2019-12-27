package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.NeedDAOFactory;
import org.jvsun.pojo.NeedPOJO;

public class NeedUpd extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8056166687728435023L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		System.out.println("***&&&**"+request.getParameter("pid"));
		BigDecimal pid = new BigDecimal(request.getParameter("pid"));
		System.out.println("********"+pid);
		String pname = request.getParameter("pname");
		String nhobby = request.getParameter("nhobby");
		NeedPOJO pojo = new NeedPOJO(pname,nhobby,pid);
		boolean flag = NeedDAOFactory.getDAOInstance().doUpd(pojo);
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