package com.hzit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzit.dao.IReaderDAO;
import com.hzit.dao.impl.ReaderDAOImpl;
import com.hzit.entity.Reader;

public class RegisterServlet extends HttpServlet {

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
		String method = request.getParameter("method");
		if(method.equals("isReaderExist")){
			isReaderExist(request,response);
		}
		if(method.equals("add")){
			add(request,response);
		}
	}

	public void isReaderExist(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		IReaderDAO ird = new ReaderDAOImpl();
		try {
			PrintWriter out = response.getWriter();
			boolean flag = ird.isReaderExist(username);
			if(flag){
				out.print("true");
			}else{
				out.print("false");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String typeid = request.getParameter("typeid");
		String name = request.getParameter("name");
		String cardid = request.getParameter("cardid");
		String tel = request.getParameter("tel");
		Reader reader = new Reader();
		reader.setUsername(username);
		reader.setPassword(password);
		reader.setGender(gender);
		reader.setTypeid(Integer.parseInt(typeid));
		reader.setName(name);
		reader.setCardid(cardid);
		reader.setTel(tel);
		
		IReaderDAO ird = new ReaderDAOImpl();
		int result = ird.add(reader);
		if(result==1){
			try {
				response.sendRedirect("../success.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.sendRedirect("../error.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
