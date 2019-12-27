package org.jvsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jvsun.dao.factory.CustomerInfoDAOFactory;
import org.jvsun.dao.factory.WorkerInfoDAOFactory;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.pojo.VLoginPOJO;
import org.jvsun.pojo.WorkerPOJO;
/**
 * 用户信息表
 * @author Administrator
 *
 */
public class UserInfo extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		VLoginPOJO Vpojo = (VLoginPOJO) request.getSession().getAttribute("pojo");
		CustomerPOJO Cpojo;
		WorkerPOJO Wpojo;
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		if(Vpojo.getUserTape()==0){//登录的用户为客户
			BigDecimal customerId = Vpojo.getUserId();
			Cpojo = CustomerInfoDAOFactory.getDAOInstance().findByWorkerId(customerId);
			sb.append("<input type='hidden' name='customerId' value="+Cpojo.getCustomerId()+">");
			sb.append("<span>姓名：</span><input type='text' style='padding:15px;' class='input' name='customerName' value="+Cpojo.getCustomerName()+">");
			sb.append("<br/>");
			sb.append("<span>年龄：</span><input type='text' class='input' name='customerAge' value="+Cpojo.getCustomerAge()+">");
			sb.append("<br/>");
			sb.append("<span>电话：</span><input type='text' class='input' name='customerTel' value="+Cpojo.getCustomerTel()+">" +
					"<br/>" +
					"<span>地址：</span><input type='text' class='input' name='customerAdress' value="+Cpojo.getCustomerAdress()+">" +
					"<br/>" +
					"<span>公司：</span><input type='text' class='input' name='company' value="+Cpojo.getCompany()+">"+
					"<br/>");
			if(Cpojo.getCustomerSex()==1){	
				sb.append("<span>性别：</span>");
				sb.append("<input type='radio' name='customerSex' value='1' id='man' checked='checked'/>男&nbsp;&nbsp;");	 
				sb.append("<input type='radio' name='customerSex' value='0' id='woman'/>女");	 
			 }else{
				sb.append("<span>性别：</span>");
				sb.append("<input type='radio' name='customerSex' value='1' id='man' />男&nbsp;&nbsp;");	 
				sb.append("<input type='radio' name='customerSex' value='0' id='woman' checked='checked'/>女");	 
			 }
		sb.append("<br/>");
			sb.append("<div style='padding-top:30px;'class='field'> <button  class='button bg-main icon-check-square-o' onclick='CustomerInfoUpd(customerId)'> 提交</button> <a href='ModifyPassword.jsp' >修改密码</a></div>" );
			out.print(sb.toString());
			out.close();
		}
		else{//登录的用户为职工
			BigDecimal workerId = Vpojo.getUserId();
			Wpojo = WorkerInfoDAOFactory.getDAOInstance().findByWorkerId(workerId);
			sb.append("<form name = 'f'>");
			sb.append("<input type='hidden' name='workerId' value="+Wpojo.getWorkerId()+">");
			sb.append("<span>姓名：</span><input type='text'style='padding:15px;' class='input' name='workerName' value="+Wpojo.getWorkerName()+">");
			sb.append("<br/>");
			
			sb.append("<span>电话：</span><input type='text' class='input' name='workerTel' value="+Wpojo.getWorkerTel()+">" +
					"<br/>" +
					"<span>地址：</span><input type='text' class='input' name='workerAdress' value="+Wpojo.getWorkerAdress()+">" +
					"<br/>");
			if(Wpojo.getWorkerSex()==1){	
				sb.append("<span>性别：</span>");
				sb.append("<input type='radio' name='workerSex' value='1' id='man' checked='checked'/>男&nbsp;&nbsp;");	 
				sb.append("<input type='radio' name='workerSex' value='0' id='woman'/>女");	 
			 }else{
				sb.append("<span>性别：</span>");
				sb.append("<input type='radio' name='workerSex' value='1' id='man' />男&nbsp;&nbsp;");	 
				sb.append("<input type='radio' name='workerSex' value='0' id='woman' checked='checked'/>女");	 
			 }
		sb.append("<br/>"); 
			sb.append("<div class='field' style='padding-top:30px;'> <button class='button bg-main icon-check-square-o' onclick='WorkerInfoUpd(workerId)'> 提交</button>" +
					"<a href='ModifyPassword.jsp' >修改密码</a> </div>" +
					"</form>");
			out.print(sb.toString());
			out.close();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
