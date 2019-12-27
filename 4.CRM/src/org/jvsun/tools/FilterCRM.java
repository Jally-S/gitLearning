package org.jvsun.tools;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class FilterCRM implements Filter {
	public void destroy() {
		System.out.println("销毁过滤器");
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String path = request.getContextPath();//获取根路径
		request.setCharacterEncoding("utf-8");//统一编码格式
		String url = request.getRequestURI();//获取请求页面的相对路径
		HttpSession session = request.getSession();//获取本次会话中的session值
		
		//过滤没有登录session的非法用户，过滤全部，放行登录相关模块，如登陆检测servlet和验证码的servlet
		if(null == session.getAttribute("pojo") 
				&& url.indexOf("login.jsp") == -1 
				&& url.indexOf("CheckLoginId") == -1 
				&& url.indexOf("VerifyCodeServlet") == -1){
			response.sendRedirect(path+"/manager/login.jsp");
		}
		else{
			arg2.doFilter(arg0, arg1);//不符合过滤条件，向下转发
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化过滤器");
		
	}


}
