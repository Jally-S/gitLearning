package com.hzit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzit.dao.IAdminDAO;
import com.hzit.dao.IReaderDAO;
import com.hzit.dao.impl.AdminDAOImpl;
import com.hzit.dao.impl.ReaderDAOImpl;
import com.hzit.entity.Admin;
import com.hzit.entity.Reader;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("logout")){
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("../login.jsp");
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		if(type.equals("reader")){
			//���ߵ�¼
			IReaderDAO ird = new ReaderDAOImpl();
			Reader reader = ird.login(username, password);
			if(reader == null){
				out.print("false");
			}
			else{
				HttpSession session = request.getSession();
				session.setAttribute("reader", reader);
				out.print("true");
			}
		}
		if(type.equals("admin")){
			//����Ա��¼
			IAdminDAO iad = new AdminDAOImpl();
			Admin admin = iad.login(username, password);
			if(admin == null){
				out.print("false");
			}
			else{
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				out.print("true");
			}
		}
	}

}
