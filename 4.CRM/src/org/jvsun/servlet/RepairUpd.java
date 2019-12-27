package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.dao.factory.RepairDAOFactory;
/**
 * 报修单更新
 * @author Administrator
 *
 */
public class RepairUpd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal repairId = new BigDecimal(request.getParameter("repairId"));//报修单ID
		System.out.println(repairId);
		String repairContent = request.getParameter("repairContent");//报修信息
		boolean flag = RepairDAOFactory.getDAOInstance().doUpd(repairId,repairContent);//调用更新报修信息的方法
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

}
