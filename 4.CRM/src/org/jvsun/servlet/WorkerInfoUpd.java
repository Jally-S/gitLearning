package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.WorkerInfoDAOFactory;
import org.jvsun.pojo.WorkerPOJO;
/**
 * 职工个人信息更新
 * @author Administrator
 *
 */
public class WorkerInfoUpd extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BigDecimal workerId = new BigDecimal(request.getParameter("workerId"));//职工ID
		String workerName = request.getParameter("workerName");//职工姓名
		int workerSex = Integer.parseInt(request.getParameter("workerSex"));//职工性别
		String workerTel = request.getParameter("workerTel");//职工 的联系电话
		String workerAdress = request.getParameter("workerAdress");//职工住址
		WorkerPOJO pojo = new WorkerPOJO(workerId,workerName,workerSex,workerTel,workerAdress);
		boolean flag = WorkerInfoDAOFactory.getDAOInstance().doUpd(pojo);//调用更新职工个人信息的方法
		PrintWriter out = response.getWriter();
		out.print(flag);
		out.close();
	}

}
