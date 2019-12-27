package com.hzit.filter;

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

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//�жϵ�ǰ�û��Ƿ��¼
		//�����¼,��������ִ��
		//���δ��¼,����ת��login.jspҳ��,���û���¼
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		if(session.getAttribute("admin") != null ){
			chain.doFilter(request, response);
		}else if(session.getAttribute("reader") != null){
			chain.doFilter(request, response);
		}else{
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.sendRedirect("login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
