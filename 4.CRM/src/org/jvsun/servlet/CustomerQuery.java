package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerDAOFactory;
import org.jvsun.pojo.CustomerPOJO;

public class CustomerQuery extends HttpServlet {
	private static final long serialVersionUID = 1225972715083060475L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		if(request.getParameter("customerId")!=null){//如果ID有输入，显示该ID的客户
		BigDecimal customerId = new BigDecimal(request.getParameter("customerId"));
		CustomerPOJO pojo = CustomerDAOFactory.getDAOInstance().findById(customerId);
		request.setAttribute("pojo", pojo);
		String str="";
		if(pojo.getCustomerSex()==1){
			str="男";
		}else{
			str="女";
		}
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>客户ID</th><th>客户名称</th><th>客户密码</th><th>客户性别</th><th>联系方式</th><th>客户住址</th><th>购买产品id</th><th>操作</th></tr>");
		sb.append("<tr>" +
				"<td>"+pojo.getCustomerId()+"</td>" +
				"<td>"+pojo.getCustomerName()+"</td>" +
				"<td>"+str+"</td>" +
				"<td>"+pojo.getCustomerTel()+"</td>" +
				"<td>"+pojo.getCustomerAdress()+"</td>" +
				"<td>"+pojo.getCustomerAge()+"</td>" +
				"<td>"+pojo.getCacount()+"</td>" +
				"<td>"+pojo.getCompany()+"</td>" +
				"<td>"+pojo.getRoleMark()+"</td>" +
				"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getCustomerId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' class='button border-red' href='#' onclick='goDelete("+pojo.getCustomerId()+")'>删除</a></td>" +
				"</tr>");
				sb.append("</table>");
				out.print(sb.toString());
				out.close();
		}
		else if(request.getParameter("customerName")!=null){//如果名字有输入，显示该姓名的客户
			String customerName = request.getParameter("customerName");
			CustomerPOJO pojo = CustomerDAOFactory.getDAOInstance().findByName(customerName);
			request.setAttribute("pojo", pojo);
			String str="";
			if(pojo.getCustomerSex()==1){
				str="男";
			}else{
				str="女";
			}
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>客户ID</th><th>客户名称</th><th>客户密码</th><th>客户性别</th><th>联系方式</th><th>客户住址</th><th>购买产品id</th><th>操作</th></tr>");
			sb.append("<tr>" +
					"<td>"+pojo.getCustomerId()+"</td>" +
					"<td>"+pojo.getCustomerName()+"</td>" +
					"<td>"+str+"</td>" +
					"<td>"+pojo.getCustomerTel()+"</td>" +
					"<td>"+pojo.getCustomerAdress()+"</td>" +
					"<td>"+pojo.getCustomerAge()+"</td>" +
					"<td>"+pojo.getCacount()+"</td>" +
					"<td>"+pojo.getCompany()+"</td>" +
					"<td>"+pojo.getRoleMark()+"</td>" +
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getCustomerId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' href='#' onclick='goDelete("+pojo.getCustomerId()+")'>删除</a></td>" +
					"</tr>");
					sb.append("</table>");
					out.print(sb.toString());
					out.close();
		}else{//ID 和名字都没有输入，显示全部客户列表
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));//得到一页显示的数据笔数
			int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));//得到要显示哪一页的数据
			List<CustomerPOJO> list = CustomerDAOFactory.getDAOInstance().findAll(pageSize, pageCurrent);
			int count=CustomerDAOFactory.getDAOInstance().findAllCount();
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' id='count' value='"+count+"'/>");
			sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>客户ID</th><th>客户名称</th><th>客户密码</th><th>客户性别</th><th>联系方式</th><th>客户住址</th><th>客户年龄</th><th>客户账号</th><th>所属公司</th><th>操作</th></tr>");
			for(CustomerPOJO pojo : list){
				String str="";
				if(pojo.getCustomerSex()==1){
					str="男";
				}else{
					str="女";
				}
				sb.append("<tr>" +
						"<td>"+pojo.getCustomerId()+"</td>" +
						"<td>"+pojo.getCustomerName()+"</td>" +
						"<td>"+str+"</td>" +
						"<td>"+pojo.getCustomerTel()+"</td>" +
						"<td>"+pojo.getCustomerAdress()+"</td>" +
						"<td>"+pojo.getCustomerAge()+"</td>" +
						"<td>"+pojo.getCacount()+"</td>" +
						"<td>"+pojo.getCompany()+"</td>" +
						"<td>"+pojo.getRoleMark()+"</td>" +
						"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getCustomerId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='button border-red' href='#' onclick='goDelete("+pojo.getCustomerId()+")'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<a class='button border-main' href='#' onclick='cusPro("+pojo.getCustomerId()+")'>客户产品维护</a></td>" +
						
						"</tr>");
			}
			sb.append("</table>");
			sb.append("<input type='button' id='first' value='|<' onclick='query(1)'/>");
			sb.append("<input type='button' id='up' value='<' onclick='query(2)'/>");
			sb.append("<input type='button' id='next' value='>' onclick='query(3)'/>");
			sb.append("<input type='button' id='end' value='>|' onclick='query(4)'/>");
			sb.append("<span id='showPageMessage'></span>");
			out.print(sb.toString());
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
