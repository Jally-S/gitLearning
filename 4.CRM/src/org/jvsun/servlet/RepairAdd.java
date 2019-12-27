package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.RepairDAOFactory;
import org.jvsun.pojo.RepairPOJO;
import org.jvsun.pojo.VLoginPOJO;
/**
 * 添加报修单
 * @author Administrator
 *
 */
public class RepairAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");//设置相应的MIME类型
		VLoginPOJO Vpojo =(VLoginPOJO)request.getSession().getAttribute("pojo");//获取登录的对象
		BigDecimal customerId = Vpojo.getUserId();//获取客户id
		String productName = request.getParameter("productName");//产品名
		String repairContent = request.getParameter("repairContent");//反馈信息
		Date time = new Date();
		RepairPOJO pojo = new RepairPOJO(productName,repairContent,time,0,customerId,"",1);
		boolean flag = RepairDAOFactory.getDAOInstance().doIns(pojo);//调用添加反馈的方法
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

}
