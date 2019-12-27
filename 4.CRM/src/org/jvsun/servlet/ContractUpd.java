package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ContractDAOFactory;
import org.jvsun.pojo.ContractPOJO;

public class ContractUpd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		BigDecimal cid = new BigDecimal(request.getParameter("cid"));
		BigDecimal customerName = new BigDecimal(request.getParameter("customerName"));
		BigDecimal workerName = new BigDecimal(request.getParameter("workerName"));
		String contractName = request.getParameter("contractName");
		String contractContent = request.getParameter("contractContent");
		int isPhoto = Integer.parseInt(request.getParameter("isPhoto"));
		ContractPOJO pojo = new ContractPOJO(cid, customerName,workerName,contractName,contractContent,isPhoto);
		boolean flag = ContractDAOFactory.getDAOInstance().doUpd(pojo);
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