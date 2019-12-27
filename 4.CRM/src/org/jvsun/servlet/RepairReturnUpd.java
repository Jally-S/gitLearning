package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RepairReturnDAOFactory;
import org.jvsun.pojo.RepairReturnPOJO;

public class RepairReturnUpd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal reptId = new BigDecimal(request.getParameter("reptId"));
		String reptName = request.getParameter("reptName");
		String reptContent = request.getParameter("reptContent");
		BigDecimal repairId =new BigDecimal(request.getParameter("repairId"));
		Date time = new Date();
		RepairReturnPOJO pojo = new RepairReturnPOJO(reptId,reptName,reptContent,time,1,repairId);
		boolean flag = RepairReturnDAOFactory.getDAOInstance().doUpd(pojo);
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
