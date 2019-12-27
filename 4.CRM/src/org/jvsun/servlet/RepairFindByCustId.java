package org.jvsun.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RepairDAOFactory;
import org.jvsun.pojo.RepairPOJO;
/**
 * 查询单个反馈单
 * @author Administrator
 *
 */
public class RepairFindByCustId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("repairId")+"**************");
		BigDecimal repairId= new BigDecimal(request.getParameter("repairId"));//反馈单ID
		RepairPOJO rpojo = RepairDAOFactory.getDAOInstance().findByRepairId(repairId);//调用查询单笔反馈的方法
		request.setAttribute("rpojo", rpojo);
		request.getRequestDispatcher("/manager/RepairUpd.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
