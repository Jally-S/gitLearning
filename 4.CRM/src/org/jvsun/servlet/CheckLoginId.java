package org.jvsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jvsun.dao.factory.UserLoginDAOFactory;
import org.jvsun.pojo.VLoginPOJO;
import org.jvsun.tools.StringHelper;



public class CheckLoginId extends HttpServlet {
	private static final long serialVersionUID = -983858420051241035L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String verifyCode=request.getParameter("verifyCode");
		String loginAccount = request.getParameter("loginacount");
		HttpSession sv = request.getSession();
		// 获得 在 会话 中存储的那个 为登录进行验证的 验证码
		final String code = (String)sv.getAttribute("/CRM8/VerifyCodeServlet");
		System.out.println( "session code : " + code );
		String password = request.getParameter("password");
		if(StringHelper.equals( verifyCode , code )){
		boolean flag = UserLoginDAOFactory.getDAOInstance().checkLogin(loginAccount,password);
		VLoginPOJO pojo =UserLoginDAOFactory.getDAOInstance().findUserByLogin(loginAccount);
		
		if(flag){										
			HttpSession session = request.getSession(true);
			session.setAttribute("pojo",pojo);
			response.sendRedirect(path+"/manager/index.jsp");
		}else{
			response.sendRedirect(path+"/manager/login.jsp");
		}
	}else{
		response.sendRedirect(path+"/manager/login.jsp");
	}
	}
}
