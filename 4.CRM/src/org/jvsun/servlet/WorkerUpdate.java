package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.WorkerPOJO;



public class WorkerUpdate extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal workerId = new BigDecimal(request.getParameter("workerId"));
		String workerName = request.getParameter("workerName");
		int  workerSex = Integer.parseInt(request.getParameter("workerSex"));
		String workerTel = request.getParameter("workerTel");
		String workerAdress = request.getParameter("workerAdress");
		String wAccount = request.getParameter("wAccount");
		int isdelete =Integer.parseInt( request.getParameter("isdelete"));
	
		WorkerPOJO pojo = new WorkerPOJO(workerId,workerName,workerSex,
				workerTel,workerAdress,wAccount,isdelete);
		boolean flag = WorkerDAOFactory.getDAOInstance().doUpd(pojo);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();

		
	}

}
