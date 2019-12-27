package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.FeedBackDAOFactory;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.pojo.ProductPOJO;
import org.jvsun.pojo.VLoginPOJO;
/**
 * 选择某个客户已购买的产品的下拉列表
 * @author Administrator
 *
 */
public class SelectProduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");//设置相应的MIME类型
			VLoginPOJO Vpojo = (VLoginPOJO) request.getSession().getAttribute("pojo");//取得session属性pojo
			BigDecimal customerId = Vpojo.getUserId();//用pojo取得客户Id
			List<ProductPOJO> list = FeedBackDAOFactory.getDAOInstance().findByCustomerId(customerId);//调用查找产品名的方法
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			//输出下拉列表
			sb.append("<select name='productName'  class='input'>请选择");
			for(ProductPOJO pojo:list){
				String productName = pojo.getProductName();
				sb.append("<option>"+productName+"</option>");
			}
			sb.append("</select>");
			out.print(sb.toString());
			
	}

}
