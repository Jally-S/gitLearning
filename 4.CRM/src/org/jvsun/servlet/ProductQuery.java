package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.ProductDAOFactory;
import org.jvsun.pojo.ProductPOJO;

public class ProductQuery extends HttpServlet {
	private static final long serialVersionUID = -2210775262017853500L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pname = request.getParameter("pname");
		int price = Integer.parseInt(request.getParameter("price"));
		int pclass = Integer.parseInt(request.getParameter("pclass"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<ProductPOJO> list = ProductDAOFactory.getDAOInstance().findAllByNamePriceClass(pname, price, pclass, pageSize, pageCurrent);
		int count = ProductDAOFactory.getDAOInstance().findAllCount();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='count' value='"+count+"'/>");
		sb.append("<table id='mytable' class='table table-hover text-center'><tr><th>产品名</th><th>产品描述</th><th>产品类别</th><th>产品库存</th><th>产品单价</th><th>操作</th></tr>");
		for(ProductPOJO pojo : list){
			String pc="";
			if(pojo.getProductClass()==1){
				pc="旗舰版";
			}
			else if(pojo.getProductClass()==2){
				pc="豪华版";
			}
			else {
				pc="性价比版";
			}
			sb.append("<tr>" +
					"<td>"+pojo.getProductName()+"</td>" +
					"<td>"+pojo.getProductDescribe()+"</td>" +
					"<td>"+pc+"</td>" +
					"<td>"+pojo.getProductInventory()+"</td>" +
					"<td>"+pojo.getProductPrice()+"</td>" +
					"<td><a class='button border-main' href='#' onclick='goUpdate("+pojo.getProductId()+")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' class='button border-red' onclick='goDelete("+pojo.getProductId()+")'>删除</a></td>" +
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
