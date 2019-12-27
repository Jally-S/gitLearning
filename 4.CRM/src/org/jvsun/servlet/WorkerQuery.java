package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.WorkerDAOFactory;
import org.jvsun.pojo.WorkerPOJO;



public class WorkerQuery extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String workerName = request.getParameter("workerName");
		int isDelete = Integer.parseInt(request.getParameter("isDelete"));
	
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<WorkerPOJO> list = WorkerDAOFactory.getDAOInstance().findAll(workerName, isDelete,
				 pageSize,pageCurrent);
		int count = WorkerDAOFactory.getDAOInstance().findCountByNameState(workerName, isDelete);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();//worker_id, worker_name, worker_sex, "
		//+ "worker_tel, worker_adress, w_acount, is_delete 
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>职工姓名</th><th>职工性别</th><th>职工电话</th><th>职工地址</th><th>职工账号</th><th>在职状态</th><th>操作</th></tr>");
		for(WorkerPOJO pojo : list){
		
			String state= "";
			if(pojo.getIsDelete() == 1){
				state = "在职";
			}
			else{
				
				state = "离职";
			}
			
			String sex= "";
			if(pojo.getWorkerSex() == 1){
				sex = "男";}
			else{
				sex = "女";
			}
			sb.append("<tr>" +
					
					"<td>"+pojo.getWorkerName()+"</td>" +
					"<td>"+sex+"</td>" +
					"<td>"+pojo.getWorkerTel()+"</td>" +
					"<td>"+pojo.getWorkerAdress()+"</td>" +
					"<td>"+pojo.getwAccount()+"</td>" +
					"<td>"+state+"</td>" +
					"<td> <a href='#' class='button border-main' onclick='goUpdate("+pojo.getWorkerId()+")'>修改</a></td>" +
					"</tr>");
		}
		sb.append("</table>");
		out.print(sb.toString());
		out.close();

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
