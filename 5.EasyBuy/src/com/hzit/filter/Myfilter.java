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

public class Myfilter implements Filter {

	private String encoding = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 设置编码
		// 将对象强转为子类
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 设置编码的时候不需要强转为子类 直接父类就可以
		req.setCharacterEncoding(encoding);
		resp.setContentType("text/html;charset=" + encoding);
		// 让过滤器继续执行
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
