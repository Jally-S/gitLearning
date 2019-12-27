package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ContractDAOFactory;
import org.jvsun.pojo.ContractPOJO;

public class ContractQuery extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6803540345846450952L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String name = request.getParameter("name");
		String wname = request.getParameter("wname");
		String cname = request.getParameter("cname");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<ContractPOJO> list = ContractDAOFactory.getDAOInstance().findAllByNameWnameCname(name, wname, cname, pageSize, pageCurrent);
		int count = ContractDAOFactory.getDAOInstance().findAllCount();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>客户名</th><th>职工名</th><th>合同名</th><th>合同内容</th><th>是否拍照</th><th>操作</th></tr>");
		for(ContractPOJO pojo : list){
			String isp="";
			if(pojo.getIsPhoto()==1){
				isp="已拍照";
			}else{
				isp="待拍照";
			}
			sb.append("<tr>" +
					"<td>"+pojo.getCustomerName()+"</td>" +
					"<td>"+pojo.getWorkerName()+"</td>" +
					"<td>"+pojo.getContractName()+"</td>" +
					"<td>"+pojo.getContractContent()+"</td>" +
					"<td>"+isp+"</td>" +
					"<td><a href='#' class='button border-main' onclick='goUpdate("+pojo.getContractId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' href='#' onclick='goDelete("+pojo.getContractId()+")'>删除</a>" +
							"&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-main' href='#' onclick='addPhoto("+pojo.getContractId()+")'>添加合同照片</a>"+
									"&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-main' href='#' onclick='kanPhoto("+pojo.getContractId()+")'>查看合同照片</a></td>" +
					"</tr>");
		}
		sb.append("</table>");
		out.print(sb.toString());
		out.close();
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
