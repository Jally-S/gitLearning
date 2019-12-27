package org.jvsun.tools;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {  
    private String encoding;  
    private Map<String, String> params = new HashMap<String, String>();  
    public void destroy() {  
    	System.out.println("销毁过滤器"); 
        params=null;  
        encoding=null;  
    }  
    public void doFilter(ServletRequest req, ServletResponse resp,  
            FilterChain chain) throws IOException, ServletException {  
        req.setCharacterEncoding(encoding);  
        chain.doFilter(req, resp);        
    }  
   
    public void init(FilterConfig config) throws ServletException {  
        System.out.println("初始化拦截器");  
        encoding = config.getInitParameter("encoding");  
        for (Enumeration e = config.getInitParameterNames(); 
        	e.hasMoreElements();) {  
            String name = (String) e.nextElement();  
            String value = config.getInitParameter(name);  
            params.put(name, value);  
        }  
    }  
 }  