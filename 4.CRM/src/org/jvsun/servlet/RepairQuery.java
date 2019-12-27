package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jvsun.dao.factory.RepairDAOFactory;
import org.jvsun.pojo.RepairPOJO;
/**
 * 报修单查询
 * @author Administrator
 *
 */
public class RepairQuery extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String productName = request.getParameter("productName");//产品名
		BigDecimal customerId =new BigDecimal(request.getParameter("customerId"));//客户ID
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<RepairPOJO> list = RepairDAOFactory.getDAOInstance().findByProduct(productName, customerId, pageSize, pageCurrent);
		int count = RepairDAOFactory.getDAOInstance().findCountById(customerId);//调用查询报修单笔数的方法
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		//输出表单
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>产品名称</th><th>报修信息</th><th>报修时间</th><th>报修结果</th><th>操作</th></tr>");
		for(RepairPOJO pojo : list){
			String reptContent;
			if(null==pojo.getReptContent()){
				   reptContent="暂未处理";
			}else{
				   reptContent=pojo.getReptContent();
			}
			sb.append("<tr>" +
					"<td>"+pojo.getProductName()+"</td>" +
					"<td>"+pojo.getRepairContent()+"</td>" +
					"<td>"+pojo.getUpDate()+"</td>" +
					"<td>"+reptContent+"</td>"+	
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getRepairId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
							"<a class='button border-red' href='#' onclick='goDelete("+pojo.getRepairId()+")'>删除</a></td>" +
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
