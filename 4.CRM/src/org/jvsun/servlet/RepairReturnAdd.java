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

public class RepairReturnAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String reptName = request.getParameter("reptName");
		String reptContent = request.getParameter("reptContent");
		BigDecimal repairId =new BigDecimal(request.getParameter("repairId")) ;
		Date time = new Date();
		RepairReturnPOJO pojo = new RepairReturnPOJO (reptName,reptContent,time,1,repairId);
		boolean flag = RepairReturnDAOFactory.getDAOInstance().doIns(pojo);
		PrintWriter out = response.getWriter();
		String s = "";
		if(flag==false){
			s="0";
		}else{
			s="1";
		}
		out.print(s);
		out.close();
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
